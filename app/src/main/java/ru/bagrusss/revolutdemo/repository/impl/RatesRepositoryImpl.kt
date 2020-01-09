package ru.bagrusss.revolutdemo.repository.impl

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject
import ru.bagrusss.revolutdemo.mappers.impl.RatesMapper
import ru.bagrusss.revolutdemo.net.api.RatesService
import ru.bagrusss.revolutdemo.net.gateways.Gateway
import ru.bagrusss.revolutdemo.providers.ResourcesProvider
import ru.bagrusss.revolutdemo.repository.RatesRepository
import ru.bagrusss.revolutdemo.rates.models.Rate
import javax.inject.Inject

/**
 * Created by bagrusss on 13.08.2019
 */
class RatesRepositoryImpl @Inject constructor(
    ratesService: RatesService,
    private val ratesMapper: RatesMapper,
    private val resourcesProvider: ResourcesProvider
) : Gateway<RatesService>(ratesService), RatesRepository {

    private val cachedRates = mutableListOf<Rate>()
    private val ratesPublisher = PublishSubject.create<List<Rate>>()

    override var currentBaseRate: Pair<String, Double> = DEFAULT_TITLE to DEFAULT_COST
        set(value) {
            synchronized(cachedRates) {
                val (newRate) = value
                val (currentRate, currentCost) = currentBaseRate
                if (newRate != currentRate) {
                    val (description, image) = resourcesProvider.rateImageAndDescription(currentRate)
                    val oldBaseRate = Rate(
                        title = currentRate,
                        description = description,
                        imgUrl = image,
                        cost = currentCost
                    )
                    val position = cachedRates.indexOfFirst { it.title == newRate }
                    cachedRates[position] = oldBaseRate
                }
                field = value
                ratesPublisher.onNext(cachedRates)
            }
        }

    override val actualRates by lazy {
        Single.fromCallable { currentBaseRate.first }
              .flatMap(service::getRates)
              .map { ratesMapper.map(it.rates) }
              .doOnSuccess {
                  synchronized(cachedRates) {
                      cachedRates.clear()
                      cachedRates.addAll(it)
                  }
              }
    }

    override val currentCostChanges: Observable<List<Rate>> = ratesPublisher.hide()

    companion object {
        private const val DEFAULT_TITLE = "EUR"
        private const val DEFAULT_COST = 100.0
    }

}