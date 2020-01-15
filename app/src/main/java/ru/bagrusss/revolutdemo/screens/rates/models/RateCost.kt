package ru.bagrusss.revolutdemo.screens.rates.models

import java.math.BigDecimal

/**
 * Created by bagrusss on 15.01.2020
 */

data class RateCost(
    @JvmField val title: String,
    var cost: BigDecimal
)