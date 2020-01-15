package ru.bagrusss.revolutdemo.repository

import io.reactivex.Observable
import io.reactivex.Single
import java.math.BigDecimal

/**
 * Created by bagrusss on 13.08.2019
 */
interface RatesRepository {
    var currentBaseRate: Pair<String, BigDecimal>
    val currentCostChanges: Observable<List<Pair<String, BigDecimal>>>
    val actualRates: Observable<List<Pair<String, BigDecimal>>>
}