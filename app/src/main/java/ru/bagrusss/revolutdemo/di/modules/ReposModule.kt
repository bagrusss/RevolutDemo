package ru.bagrusss.revolutdemo.di.modules

import dagger.Binds
import dagger.Module
import ru.bagrusss.revolutdemo.repository.RatesRepository
import ru.bagrusss.revolutdemo.net.gateways.impl.RatesRepositoryImpl
import javax.inject.Singleton

/**
 * Created by bagrusss on 13.08.2019
 */
@Module
interface ReposModule {

    @Binds
    @Singleton
    fun bindRatesGateway(impl: RatesRepositoryImpl): RatesRepository

}