package ru.bagrusss.revolutdemo.repository.impl

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import ru.bagrusss.revolutdemo.mappers.rates.RatesMapper
import ru.bagrusss.revolutdemo.net.api.RatesService
import ru.bagrusss.revolutdemo.net.gateways.Gateway
import ru.bagrusss.revolutdemo.repository.RatesRepository
import ru.bagrusss.revolutdemo.screens.rates.models.RateCost
import ru.bagrusss.revolutdemo.storage.RatesCostStorage
import ru.bagrusss.revolutdemo.util.rx.observable
import java.math.BigDecimal
import javax.inject.Inject

/**
 * Created by bagrusss on 13.08.2019
 */
class RatesRepositoryImpl @Inject constructor(
    ratesService: RatesService,
    private val cachedRatesStorage: RatesCostStorage,
    private val ratesMapper: RatesMapper
) : Gateway<RatesService>(ratesService), RatesRepository {

    private val costPublisher = PublishSubject.create<Unit>()

    init {
        cachedRatesStorage.currentRates = mutableListOf(RateCost(DEFAULT_TITLE, DEFAULT_COST))
    }

    override var currentBaseRate = DEFAULT_TITLE
        set(value) {
            if (field != value) {
                cachedRatesStorage.selectPrimaryRate(value)
                field = value
                costPublisher.onNext(Unit)
            }
        }

    override var currentCost: BigDecimal = BigDecimal.ONE
        set(value) {
            field = value
            costPublisher.onNext(Unit)
        }

    override val actualRates: Observable<List<RateCost>> by lazy {
        observable { currentBaseRate }
            .filter { currentCost > BigDecimal.ZERO }
            .map { currentBaseRate }
            .flatMapSingle(service::getRates)
            .map { response ->
                val mappedRates = ratesMapper.map(response.rates)
                val base = RateCost(response.base, DEFAULT_COST)
                val actualRates = mutableListOf(base).apply { addAll(mappedRates) }
                cachedRatesStorage.currentRates = actualRates
                cachedRatesStorage.currentRates
            }
    }

    override val costChanges: Observable<List<RateCost>> = costPublisher.hide()
        .map { cachedRatesStorage.currentRates }

    companion object {
        private const val DEFAULT_TITLE = "EUR"
        private const val DEFAULT_COST = 1.0
    }

}