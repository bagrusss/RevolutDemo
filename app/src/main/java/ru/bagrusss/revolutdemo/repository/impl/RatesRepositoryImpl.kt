package ru.bagrusss.revolutdemo.repository.impl

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import ru.bagrusss.revolutdemo.mappers.rates.RatesMapper
import ru.bagrusss.revolutdemo.net.api.RatesService
import ru.bagrusss.revolutdemo.net.gateways.Gateway
import ru.bagrusss.revolutdemo.repository.RatesRepository
import ru.bagrusss.revolutdemo.screens.rates.models.RateCost
import ru.bagrusss.revolutdemo.util.rx.observable
import timber.log.Timber
import java.math.BigDecimal
import java.util.*
import javax.inject.Inject

/**
 * Created by bagrusss on 13.08.2019
 */
class RatesRepositoryImpl @Inject constructor(
    ratesService: RatesService,
    private val ratesMapper: RatesMapper
) : Gateway<RatesService>(ratesService), RatesRepository {

    private val cachedRates = mutableListOf(RateCost(DEFAULT_TITLE, DEFAULT_COST))
    private val costPublisher = PublishSubject.create<List<RateCost>>()

    override var currentBaseRate: RateCost = cachedRates.first()
        set(value) {
            synchronized(cachedRates) {
                val (newRate) = value
                val (oldRate) = field
                if (oldRate != newRate) {
                    val newRateIndex = cachedRates.indexOfFirst { it.title == newRate }
                    if (newRateIndex != -1) {
                        val newRateItem = cachedRates.removeAt(newRateIndex)
                            .apply { cost = BigDecimal.ONE }
                        cachedRates.add(0, newRateItem)
                        for (i in 1 until cachedRates.size) {
                            val rate = cachedRates[i]
                            cachedRates[i].cost = rate.cost / newRateItem.cost
                        }
                    }
                }
                field = value
                costPublisher.onNext(cachedRates)
            }
        }

    override val actualRates: Observable<List<RateCost>> by lazy {
        observable { currentBaseRate }
            .filter { (_, cost) -> cost > BigDecimal.ZERO }
            .map { (rate, _) -> rate }
            .flatMapSingle(service::getRates)
            .map { response ->
                val mappedRates = ratesMapper.map(response.rates)
                synchronized(cachedRates) {
                    val base = RateCost(response.base, DEFAULT_COST)
                    val actualRates = mutableListOf(base).apply { addAll(mappedRates) }
                    mergeRates(actualRates, cachedRates)
                    Timber.d("Rates size ${cachedRates.size} | first ${cachedRates.first()}")
                    cachedRates.toList()
                }
            }
    }

    override val costChanges: Observable<List<RateCost>> = costPublisher.hide()

    private fun mergeRates(from: List<RateCost>, to: MutableList<RateCost>) {
        from.forEach { newRate ->
            val oldRate = to.firstOrNull { it.title == newRate.title }
            if (oldRate == null) {
                to.add(newRate)
            } else {
                oldRate.cost = newRate.cost
            }
        }
        val indexesToRemove = LinkedList<Int>()
        for (i in 1 until to.size) {
            val oldRate = to[i]
            val newRateIndex = from.indexOfFirst { it.title == oldRate.title }
            if (newRateIndex == -1) {
                indexesToRemove.add(i)
            }
        }
        indexesToRemove.forEach {
            to.removeAt(it)
        }
    }

    companion object {
        private const val DEFAULT_TITLE = "EUR"
        private val DEFAULT_COST = BigDecimal.ONE
    }

}