package ru.bagrusss.revolutdemo.screens.rates.di

import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import ru.bagrusss.revolutdemo.screens.rates.RatesActivity

/**
 * Created by bagrusss on 13.08.2019
 */

@Module(subcomponents = [RatesSubComponent::class])
interface RatesInjectModule {

    @Binds
    @IntoMap
    @ClassKey(RatesActivity::class)
    fun bindMainActivityInjectorFactory(builder: RatesSubComponent.Factory): AndroidInjector.Factory<*>

}