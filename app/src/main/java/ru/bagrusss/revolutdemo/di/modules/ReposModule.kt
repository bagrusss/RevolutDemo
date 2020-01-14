package ru.bagrusss.revolutdemo.di.modules

import dagger.Binds
import dagger.Module
import ru.bagrusss.revolutdemo.repository.RatesRepository
import ru.bagrusss.revolutdemo.repository.impl.RatesRepositoryImpl

/**
 * Created by bagrusss on 13.08.2019
 */
@Module
interface ReposModule {

    @Binds
    fun bindRatesGateway(impl: RatesRepositoryImpl): RatesRepository

}