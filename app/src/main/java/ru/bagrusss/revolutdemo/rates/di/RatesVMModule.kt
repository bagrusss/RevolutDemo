package ru.bagrusss.revolutdemo.rates.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.bagrusss.revolutdemo.rates.RatesInteractor
import ru.bagrusss.revolutdemo.rates.RatesInteractorImpl
import ru.bagrusss.revolutdemo.rates.RatesVM
import javax.inject.Scope

/**
 * Created by bagrusss on 13.08.2019
 */
@Module
class RatesVMModule {

    @Provides
    @RatesScope
    fun provideVM(interactor: RatesInteractor) = RatesVM(interactor)

}

@Module
interface RatesInteractorModule {

    @Binds
    @RatesScope
    fun bindIntractor(impl: RatesInteractorImpl): RatesInteractor

}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class RatesScope