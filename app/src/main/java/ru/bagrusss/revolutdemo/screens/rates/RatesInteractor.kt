package ru.bagrusss.revolutdemo.screens.rates

import io.reactivex.Completable
import io.reactivex.Observable
import ru.bagrusss.revolutdemo.mvvm.Interactor
import ru.bagrusss.revolutdemo.screens.rates.models.Rate

/**
 * Created by bagrusss on 13.08.2019
 */
interface RatesInteractor : Interactor {

    val ratesUpdates: Observable<List<Rate>>
    val rateChange: Completable

    fun rateChanged(rate: String, cost: String)

}