package ru.bagrusss.revolutdemo.repository

import io.reactivex.Observable
import io.reactivex.Single
import ru.bagrusss.revolutdemo.screens.rates.models.RateCost
import java.math.BigDecimal

/**
 * Created by bagrusss on 13.08.2019
 */
interface RatesRepository {
    var currentBaseRate: RateCost
    val costChanges: Observable<List<RateCost>>
    val actualRates: Observable<List<RateCost>>
}