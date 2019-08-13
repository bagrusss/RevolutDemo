package ru.bagrusss.revolutdemo.di.modules

import dagger.Binds
import dagger.Module
import ru.bagrusss.revolutdemo.net.gateways.RatesGateway
import ru.bagrusss.revolutdemo.net.gateways.impl.RatesGatewayImpl
import javax.inject.Singleton

/**
 * Created by bagrusss on 13.08.2019
 */
@Module
interface GatewaysModule {

    @Binds
    @Singleton
    fun bindRatesGateway(impl: RatesGatewayImpl): RatesGateway

}