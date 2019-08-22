package ru.bagrusss.revolutdemo.net.gateways

import io.reactivex.Single
import ru.bagrusss.revolutdemo.rates.models.Rate

/**
 * Created by bagrusss on 13.08.2019
 */
interface RatesRepository {
    val rates: Single<List<Rate>>
}