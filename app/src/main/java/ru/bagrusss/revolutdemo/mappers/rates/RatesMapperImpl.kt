package ru.bagrusss.revolutdemo.mappers.rates

import java.math.BigDecimal
import javax.inject.Inject

/**
 * Created by bagrusss on 23.09.2019
 */
class RatesMapperImpl @Inject constructor() : RatesMapper {

    override fun map(from: Map<String, Double>): List<Pair<String, BigDecimal>> =
        from.map { (rate, cost) ->
            rate to cost.toBigDecimal()
        }

}