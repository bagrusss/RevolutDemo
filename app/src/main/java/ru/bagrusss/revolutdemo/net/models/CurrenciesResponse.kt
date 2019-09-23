package ru.bagrusss.revolutdemo.net.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.math.BigDecimal

/**
 * Created by bagrusss on 12.08.2019
 */
@JsonClass(generateAdapter = true)
class RatesResponse(
    @Json(name = "date") val date: String,
    @Json(name = "base") val base: String,
    @Json(name = "rates") val rates: Map<String, BigDecimal>
)