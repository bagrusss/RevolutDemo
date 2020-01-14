package ru.bagrusss.revolutdemo.repository.impl

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import ru.bagrusss.revolutdemo.mappers.rates.RatesMapper
import ru.bagrusss.revolutdemo.net.api.RatesService
import ru.bagrusss.revolutdemo.net.gateways.Gateway
import ru.bagrusss.revolutdemo.repository.RatesRepository
import ru.bagrusss.revolutdemo.util.collections.intersectionFromSecond
import ru.bagrusss.revolutdemo.util.rx.single
import java.math.BigDecimal
import javax.inject.Inject

/**
 * Created by bagrusss on 13.08.2019
 */
class RatesRepositoryImpl @Inject constructor(
    ratesService: RatesService,
    private val ratesMapper: RatesMapper
) : Gateway<RatesService>(ratesService), RatesRepository {

    private val cachedRates: MutableList<Pair<String, BigDecimal>>
    private val ratesPublisher = PublishSubject.create<List<Pair<String, BigDecimal>>>()

    init {
        cachedRates = mutableListOf(DEFAULT_TITLE to DEFAULT_COST)
    }

    override var currentBaseRate: Pair<String, BigDecimal> = cachedRates.first()
        set(value) {
            synchronized(cachedRates) {
                val (newRate) = value
                val (oldRate) = field
                if (oldRate != newRate) {
                    val newRateIndex = cachedRates.indexOfFirst { it.first == newRate }
                    val newRateItem = cachedRates.removeAt(newRateIndex)
                    cachedRates.add(0, newRateItem.copy(second = BigDecimal.ONE))
                    for (i in 1 until cachedRates.size) {
                        val rate = cachedRates[i]
                        cachedRates[i] = rate.copy(second = rate.second / newRateItem.second)
                    }
                }
                field = value
                ratesPublisher.onNext(cachedRates)
            }
        }

    override val actualRates by lazy {
        single { currentBaseRate.first }
            .flatMap(service::getRates)
            .map {
                val mappedRates = ratesMapper.map(it.rates)
                synchronized(cachedRates) {
                    val elements = if (cachedRates.size == 1) {
                        mappedRates
                    } else {
                        cachedRates.intersectionFromSecond(mappedRates) { oldRate, newRate ->
                            oldRate.first == newRate.first
                        }
                    }
                    cachedRates.subList(1, cachedRates.size)
                        .clear()
                    cachedRates.addAll(elements)
                    cachedRates.toList()
                }
            }
    }

    override val currentCostChanges: Observable<List<Pair<String, BigDecimal>>> = ratesPublisher.hide()

    companion object {
        private const val DEFAULT_TITLE = "EUR"
        private val DEFAULT_COST = BigDecimal.ONE
    }

}