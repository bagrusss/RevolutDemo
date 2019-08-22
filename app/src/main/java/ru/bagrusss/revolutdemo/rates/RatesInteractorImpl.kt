package ru.bagrusss.revolutdemo.rates

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
    private val confirRepository: ConfigRepository,
    private val schedulers: SchedulersProvider
) : RatesInteractor {

    override val ratesChanges: Observable<List<Rate>> by lazy {
        Observable.interval(1, TimeUnit.SECONDS)
            .flatMapSingle { ratesRepo.rates }
            .observeOn(schedulers.ui)
    }

}