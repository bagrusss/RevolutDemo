package ru.bagrusss.revolutdemo.mappers.rates

import ru.bagrusss.revolutdemo.screens.rates.models.RateCost
import ru.bagrusss.revolutdemo.util.mapper.Mapper

/**
 * Created by bagrusss on 13.01.2020
 */
interface RatesMapper : Mapper<Map<String, Double>, List<RateCost>>