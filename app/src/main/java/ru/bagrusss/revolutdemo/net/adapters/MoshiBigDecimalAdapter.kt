package ru.bagrusss.revolutdemo.net.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.math.BigDecimal

/**
 * Created by bagrusss on 23.09.2019
 */
object BigDecimalAdapter {

    @FromJson
    fun fromJson(value: Double) = BigDecimal(value)

    @ToJson
    fun toJson(value: BigDecimal) = value.toDouble()

}