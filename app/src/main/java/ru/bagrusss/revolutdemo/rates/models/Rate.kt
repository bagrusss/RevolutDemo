package ru.bagrusss.revolutdemo.rates.models

/**
 * Created by bagrusss on 13.08.2019
 */

class Rate(
    @JvmField val title: String,
    @JvmField val description: String,
    @JvmField val imgUrl: String,
    @JvmField val cost: Float
)