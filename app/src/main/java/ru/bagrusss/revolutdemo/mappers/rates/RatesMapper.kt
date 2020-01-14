package ru.bagrusss.revolutdemo.mappers.rates

import ru.bagrusss.revolutdemo.mappers.Mapper
import java.math.BigDecimal

/**
 * Created by bagrusss on 13.01.2020
 */
interface RatesMapper : Mapper<Map<String, Double>, List<Pair<String, BigDecimal>>>