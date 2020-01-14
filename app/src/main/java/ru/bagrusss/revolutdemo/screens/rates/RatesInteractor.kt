package ru.bagrusss.revolutdemo.rates

import io.reactivex.Completable
import io.reactivex.Observable
import ru.bagrusss.revolutdemo.mvvm.Interactor
import ru.bagrusss.revolutdemo.rates.models.Rate
import java.math.BigDecimal

/**
 * Created by bagrusss on 13.08.2019
 */
interface RatesInteractor: Interactor {

    val ratesChanges: Observable<List<Rate>>

    fun rateChanged(rate: String, cost: String): Completable

}