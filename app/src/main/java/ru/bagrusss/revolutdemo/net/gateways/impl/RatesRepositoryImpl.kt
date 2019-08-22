package ru.bagrusss.revolutdemo.net.gateways.impl

import io.reactivex.Single
import ru.bagrusss.revolutdemo.net.api.RatesService
import ru.bagrusss.revolutdemo.net.gateways.Gateway
import ru.bagrusss.revolutdemo.net.gateways.RatesGateway
import ru.bagrusss.revolutdemo.rates.models.Rate
import javax.inject.Inject

/**
 * Created by bagrusss on 13.08.2019
 */
class RatesGatewayImpl @Inject constructor(ratesService: RatesService): Gateway<RatesService>(ratesService), RatesGateway {

    override val rates: Single<List<Rate>>
        get() = service.getRates()
                       .map {
                           it.rates.map { (k, v) ->
                               Rate(
                                   title = k,
                                   description = "",
                                   cost = v
                               )
                           }
                       }

}