package ru.bagrusss.revolutdemo.rates.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.bagrusss.revolutdemo.rates.RatesInteractor
import ru.bagrusss.revolutdemo.rates.RatesInteractorImpl
import ru.bagrusss.revolutdemo.rates.RatesVM
import ru.bagrusss.revolutdemo.rates.list.RatesAdapter
import javax.inject.Scope

/**
 * Created by bagrusss on 13.08.2019
 */

@Module
abstract class RatesModule {

    @Binds
    @RatesScope
    abstract fun bindIntractor(impl: RatesInteractorImpl): RatesInteractor

}

@Module
class RatesViewModule {

    @Provides
    @RatesScope
    fun provideRatesAdapter(vm: RatesVM) = RatesAdapter(vm)

}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class RatesScope