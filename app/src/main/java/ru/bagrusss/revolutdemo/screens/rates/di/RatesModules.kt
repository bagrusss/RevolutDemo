package ru.bagrusss.revolutdemo.screens.rates.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.bagrusss.revolutdemo.mappers.rates.RatesMapper
import ru.bagrusss.revolutdemo.mappers.rates.RatesMapperImpl
import ru.bagrusss.revolutdemo.screens.rates.RatesInteractor
import ru.bagrusss.revolutdemo.screens.rates.RatesInteractorImpl
import ru.bagrusss.revolutdemo.screens.rates.RatesVM
import ru.bagrusss.revolutdemo.screens.rates.list.RatesAdapter
import ru.bagrusss.revolutdemo.screens.rates.list.RatesItemAnimator
import javax.inject.Scope

/**
 * Created by bagrusss on 13.08.2019
 */

@Module
interface RatesModule {

    @Binds
    @RatesScope
    fun bindIntractor(impl: RatesInteractorImpl): RatesInteractor

}

@Module
class RatesViewModule {

    @Provides
    @RatesScope
    fun provideRatesAdapter(vm: RatesVM) = RatesAdapter(vm)

    @Provides
    @RatesScope
    fun provideRatesItemAnimator(vm: RatesVM) = RatesItemAnimator(vm)

}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class RatesScope