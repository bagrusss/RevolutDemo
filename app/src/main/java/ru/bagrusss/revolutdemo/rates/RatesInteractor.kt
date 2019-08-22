package ru.bagrusss.revolutdemo.rates

import io.reactivex.Observable
import ru.bagrusss.revolutdemo.mvvm.Interactor
import ru.bagrusss.revolutdemo.rates.models.Rate

/**
 * Created by bagrusss on 13.08.2019
 */
interface RatesInteractor: Interactor {

    val ratesChanges: Observable<List<Rate>>

    fun changeBaseRate(newRate: String, currentCost: Double)

}