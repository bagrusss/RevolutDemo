package ru.bagrusss.revolutdemo.net.gateways.impl

import ru.bagrusss.revolutdemo.net.api.RatesService
import ru.bagrusss.revolutdemo.net.gateways.Gateway
import ru.bagrusss.revolutdemo.net.gateways.RatesGateway
import javax.inject.Inject

/**
 * Created by bagrusss on 13.08.2019
 */
class RatesGatewayImpl @Inject constructor(ratesService: RatesService): Gateway<RatesService>(ratesService), RatesGateway {

}