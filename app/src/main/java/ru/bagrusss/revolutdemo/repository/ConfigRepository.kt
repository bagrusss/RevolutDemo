package ru.bagrusss.revolutdemo.repository

import ru.bagrusss.revolutdemo.rates.models.Rate

/**
 * Created by bagrusss on 13.08.2019
 */
interface ConfigRepository {
    var currentRate: Rate
}