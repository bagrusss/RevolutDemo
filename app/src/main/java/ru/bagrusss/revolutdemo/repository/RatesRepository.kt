package ru.bagrusss.revolutdemo.repository

import io.reactivex.Observable
import io.reactivex.Single
import ru.bagrusss.revolutdemo.rates.models.Rate
import java.math.BigDecimal

/**
 * Created by bagrusss on 13.08.2019
 */
interface RatesRepository {
    var currentBaseRate: Pair<String, Double>
    val currentCostChanges: Observable<List<Rate>>
    val actualRates: Single<List<Rate>>
}