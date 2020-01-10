package ru.bagrusss.revolutdemo.repository.impl

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject
import ru.bagrusss.revolutdemo.mappers.impl.RatesMapper
import ru.bagrusss.revolutdemo.net.api.RatesService
import ru.bagrusss.revolutdemo.net.gateways.Gateway
import ru.bagrusss.revolutdemo.providers.ResourcesProvider
import ru.bagrusss.revolutdemo.rates.models.Rate
import ru.bagrusss.revolutdemo.repository.RatesRepository
import ru.bagrusss.revolutdemo.util.collections.intersectionFromSecond
import javax.inject.Inject

/**
 * Created by bagrusss on 13.08.2019
 */
class RatesRepositoryImpl @Inject constructor(
    ratesService: RatesService,
    resourcesProvider: ResourcesProvider,
    private val ratesMapper: RatesMapper
) : Gateway<RatesService>(ratesService), RatesRepository {

    private val cachedRates: MutableList<Rate>
    private val ratesPublisher = PublishSubject.create<List<Rate>>()

    init {
        val (description, img) = resourcesProvider.rateDescriptionAndImage(DEFAULT_TITLE)
        cachedRates = mutableListOf(
            Rate(
                title = DEFAULT_TITLE,
                description = description,
                imgUrl = img,
                cost = 1.0
            )
        )
    }

    override var currentBaseRate: Pair<String, Double> = DEFAULT_TITLE to DEFAULT_COST
        set(value) {
            synchronized(cachedRates) {
                val (newRate) = value
                val newRateIndex = cachedRates.indexOfFirst { it.title == newRate }
                val newRateItem = cachedRates.removeAt(newRateIndex)
                cachedRates.add(0, newRateItem)
                field = value
                ratesPublisher.onNext(cachedRates)
            }
        }

    override val actualRates by lazy {
        Single.fromCallable { currentBaseRate.first }
            .flatMap(service::getRates)
            .map {
                val mappedRates = ratesMapper.map(it.rates)
                synchronized(cachedRates) {
                    val elements = if (cachedRates.size == 1) {
                        mappedRates
                    } else {
                        cachedRates.intersectionFromSecond(mappedRates) { oldRate, newRate -> oldRate.title == newRate.title }
                    }
                    cachedRates.subList(1, cachedRates.size)
                        .clear()
                    cachedRates.addAll(elements)
                }
                cachedRates.toList()
            }
    }

    override val currentCostChanges: Observable<List<Rate>> = ratesPublisher.hide()

    companion object {
        private const val DEFAULT_TITLE = "EUR"
        private const val DEFAULT_COST = 100.0
    }

}