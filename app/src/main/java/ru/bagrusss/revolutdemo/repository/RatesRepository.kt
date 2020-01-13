package ru.bagrusss.revolutdemo.repository

import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by bagrusss on 13.08.2019
 */
interface RatesRepository {
    var currentBaseRate: Pair<String, Double>
    val currentCostChanges: Observable<List<Pair<String, Double>>>
    val actualRates: Single<List<Pair<String, Double>>>
}