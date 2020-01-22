package ru.bagrusss.revolutdemo.screens.rates.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.bagrusss.revolutdemo.screens.rates.RatesInteractor
import ru.bagrusss.revolutdemo.screens.rates.RatesInteractorImpl
import ru.bagrusss.revolutdemo.screens.rates.RatesVM
import ru.bagrusss.revolutdemo.screens.rates.list.*
import ru.bagrusss.revolutdemo.util.recycler.ViewHolderFactory
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

    @get:Provides
    @RatesScope
    val rateDiffCallback: RatesDiffCallback
        get() = RatesDiffCallback()

    @get:Provides
    @RatesScope
    val ratesItemAnimator: RatesItemAnimator
        get() = RatesItemAnimator()

    @Provides
    @RatesScope
    fun provideRatesVHFactory(
        vm: RatesVM
    ): ViewHolderFactory<RateViewHolder> = RatesHolderFactory(vm)

    @Provides
    @RatesScope
    fun provideRatesAdapter(
        factory: ViewHolderFactory<RateViewHolder>,
        callback: RatesDiffCallback
    ) = RatesAdapter(factory, callback)

}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class RatesScope