package ru.bagrusss.revolutdemo.rates.di

import dagger.Subcomponent
import dagger.android.AndroidInjector
import ru.bagrusss.revolutdemo.rates.RatesActivity

/**
 * Created by bagrusss on 13.08.2019
 */
@Subcomponent(
    modules = [
        RatesModule::class
    ]
)
@RatesScope
interface RatesSubComponent : AndroidInjector<RatesActivity> {

    @Subcomponent.Factory
    interface Builder : AndroidInjector.Factory<RatesActivity>

}
