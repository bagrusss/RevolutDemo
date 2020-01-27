package ru.bagrusss.revolutdemo.storage

import ru.bagrusss.revolutdemo.screens.rates.models.RateCost

/**
 * Created by bagrusss on 28.01.2020
 */
interface RatesCostStorage {

    var currentRates: List<RateCost>

    fun selectPrimaryRate(title: String)

}