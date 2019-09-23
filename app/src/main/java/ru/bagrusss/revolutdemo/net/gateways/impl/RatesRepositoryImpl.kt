package ru.bagrusss.revolutdemo.net.gateways.impl

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject
import ru.bagrusss.revolutdemo.mappers.impl.RatesMapper
import ru.bagrusss.revolutdemo.net.api.RatesService
import ru.bagrusss.revolutdemo.net.gateways.Gateway
import ru.bagrusss.revolutdemo.providers.ResourcesProvider
import ru.bagrusss.revolutdemo.repository.RatesRepository
import ru.bagrusss.revolutdemo.rates.models.Rate
import java.math.BigDecimal
import javax.inject.Inject

/**
 * Created by bagrusss on 13.08.2019
 */
class RatesRepositoryImpl @Inject constructor(
    ratesService: RatesService,
    private val ratesMapper: RatesMapper,
    private val resourcesProvider: ResourcesProvider
) : Gateway<RatesService>(ratesService), RatesRepository {

    private lateinit var cachedBaseRate: Pair<String, BigDecimal>

    private val cachedRates = mutableListOf<Rate>()
    private val ratesPublisher = PublishSubject.create<List<Rate>>()

    override var currentBaseRate: Pair<String, BigDecimal>
        get() {
            if (!::cachedBaseRate.isInitialized) {
                cachedBaseRate = DEFAULT_TITLE to DEFAULT_COST
            }
            return cachedBaseRate
        }
        set(value) {
            val (rate, cost) = value
            val (currentRate, currentCost) = currentBaseRate
            if (rate != currentRate) {
                val (description, image) = resourcesProvider.rateImageAndDescription(rate)
                val baseRate = Rate(
                    title = rate,
                    description = description,
                    imgUrl = image,
                    cost = cost
                )
                cachedRates.add(0, baseRate)
                cachedRates.removeAll { it.title == currentRate }
            } else {
                val updated = cachedRates.map {
                    val updatedCost = if (currentCost > BigDecimal.ZERO)
                                          it.cost / currentCost * cost
                                      else BigDecimal.ZERO
                    it.copy(cost = updatedCost)
                }
                cachedRates.clear()
                cachedRates.addAll(updated)
            }
            cachedBaseRate = value
            ratesPublisher.onNext(cachedRates)
        }

    override val actualRates by lazy {
        Single.fromCallable { currentBaseRate.first }
              .flatMap(service::getRates)
              .map { ratesMapper.map(it.rates) }
              .doOnSuccess {
                  cachedRates.clear()
                  cachedRates.addAll(it)
              }
    }

    override val currentCostChanges: Observable<List<Rate>> = ratesPublisher.hide()

    companion object {
        private const val DEFAULT_TITLE = "EUR"
        private val DEFAULT_COST = 100.toBigDecimal()
    }

}