package ru.bagrusss.revolutdemo.rates.di

import dagger.Binds
import dagger.Module
import ru.bagrusss.revolutdemo.rates.RatesInteractor
import ru.bagrusss.revolutdemo.rates.RatesInteractorImpl
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

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class RatesScope