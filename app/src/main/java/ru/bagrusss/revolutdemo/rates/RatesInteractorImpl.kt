package ru.bagrusss.revolutdemo.rates

import io.reactivex.Completable
import io.reactivex.Observable
import ru.bagrusss.revolutdemo.repository.ConfigRepository
import ru.bagrusss.revolutdemo.repository.RatesRepository
import ru.bagrusss.revolutdemo.providers.ResourcesProvider
import ru.bagrusss.revolutdemo.providers.SchedulersProvider
import ru.bagrusss.revolutdemo.rates.models.Rate
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by bagrusss on 13.08.2019
 */
class RatesInteractorImpl @Inject constructor(
    private val ratesRepo: RatesRepository,
    private val resProvider: ResourcesProvider,
    private val configRepository: ConfigRepository,
    private val schedulers: SchedulersProvider
) : RatesInteractor {

    override val ratesChanges: Observable<List<Rate>> by lazy {
        Observable.interval(1, TimeUnit.SECONDS)
                  .startWith(0)
                  .flatMapSingle {
                      val (rate, cost) = configRepository.currentBaseRate
                      ratesRepo.actualRates(rate, cost)
                  }
                  .observeOn(schedulers.ui)
    }

    override fun rateChanged(rate: String, cost: Float) = Completable.fromAction {
        configRepository.currentBaseRate = rate to cost
    }
    .subscribeOn(schedulers.io)
    .observeOn(schedulers.ui)

}
