package ru.bagrusss.revolutdemo.di.modules

import dagger.Binds
import dagger.Module
import ru.bagrusss.revolutdemo.mappers.rates.RatesMapper
import ru.bagrusss.revolutdemo.mappers.rates.RatesMapperImpl

/**
 * Created by bagrusss on 14.01.2020
 */

@Module
interface MappersModule {

    @Binds
    fun bindRatesMapper(impl: RatesMapperImpl): RatesMapper

}