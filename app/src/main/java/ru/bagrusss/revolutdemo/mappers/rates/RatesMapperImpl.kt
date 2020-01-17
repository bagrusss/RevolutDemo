package ru.bagrusss.revolutdemo.mappers.rates

import ru.bagrusss.revolutdemo.screens.rates.models.RateCost
import javax.inject.Inject

/**
 * Created by bagrusss on 23.09.2019
 */
class RatesMapperImpl @Inject constructor() : RatesMapper {

    override fun map(from: Map<String, Double>): List<RateCost> =
        from.map { (rate, cost) ->
            RateCost(rate, cost)
        }

}