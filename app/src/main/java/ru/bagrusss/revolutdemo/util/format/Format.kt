package ru.bagrusss.revolutdemo.util.format

import java.math.BigDecimal

/**
 * Created by bagrusss on 15.01.2020
 */

val BigDecimal.preFormattedMoney: String
    get() = if (toDouble() == 0.0) "0" else toString()