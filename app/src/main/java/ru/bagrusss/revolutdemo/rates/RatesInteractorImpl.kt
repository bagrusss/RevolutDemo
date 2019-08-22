package ru.bagrusss.revolutdemo.rates

import io.reactivex.Completable
import io.reactivex.Observable
import ru.bagrusss.revolutdemo.repository.ConfigRepository
import ru.bagrusss.revolutdemo.net.gateways.RatesRepository
import ru.bagrusss.revolutdemo.providers.SchedulersProvider
import ru.bagrusss.revolutdemo.rates.models.Rate
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by bagrusss on 13.08.2019
 */
class RatesInteractorImpl @Inject constructor(
    private val ratesRepo: RatesRepository,
    private val configRepository: ConfigRepository,
    private val schedulers: SchedulersProvider
) : RatesInteractor {

    override val ratesChanges: Observable<List<Rate>> by lazy {
        Observable.interval(1, TimeUnit.SECONDS)
                  .startWith(0)
                  .flatMapSingle {
                      val currentRate = configRepository.currentRate
                      ratesRepo.actualRates(currentRate.title, currentRate.cost)
                               .map { listOf(currentRate) + it }
                  }
                  .observeOn(schedulers.ui)
    }

    override fun changeBaseRate(newRate: String, currentCost: Double) {

    }

    override fun rateChanged(rate: Rate) = Completable.fromAction {
        configRepository.currentRate = rate
    }
    .subscribeOn(schedulers.io)
    .observeOn(schedulers.ui)

}
