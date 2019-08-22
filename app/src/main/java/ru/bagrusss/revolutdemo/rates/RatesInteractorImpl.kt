package ru.bagrusss.revolutdemo.rates

import io.reactivex.Observable
import ru.bagrusss.revolutdemo.net.gateways.RatesGateway
import ru.bagrusss.revolutdemo.providers.ResourcesProvider
import ru.bagrusss.revolutdemo.providers.SchedulersProvider
import ru.bagrusss.revolutdemo.rates.models.Rate
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by bagrusss on 13.08.2019
 */
class RatesInteractorImpl @Inject constructor(
    private val ratesGateway: RatesGateway,
    private val resProvider: ResourcesProvider,
    private val schedulers: SchedulersProvider
) : RatesInteractor {

    override val ratesChanges: Observable<List<Rate>> by lazy {
        Observable.interval(1, TimeUnit.SECONDS)
            .flatMapSingle { ratesGateway.rates }
            .observeOn(schedulers.ui)
    }

}