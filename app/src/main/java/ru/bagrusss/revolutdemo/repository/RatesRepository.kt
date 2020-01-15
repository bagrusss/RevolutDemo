package ru.bagrusss.revolutdemo.repository

import io.reactivex.Observable
import ru.bagrusss.revolutdemo.screens.rates.models.RateCost

/**
 * Created by bagrusss on 13.08.2019
 */
interface RatesRepository {
    var currentBaseRate: RateCost
    val costChanges: Observable<List<RateCost>>
    val actualRates: Observable<List<RateCost>>
}