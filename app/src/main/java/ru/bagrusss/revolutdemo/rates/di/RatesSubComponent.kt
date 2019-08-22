package ru.bagrusss.revolutdemo.rates.di

import dagger.Subcomponent
import dagger.android.AndroidInjector
import ru.bagrusss.revolutdemo.rates.RatesActivity
import ru.bagrusss.revolutdemo.rates.list.RateViewHolder

/**
 * Created by bagrusss on 13.08.2019
 */
@Subcomponent(
    modules = [
        RatesModule::class,
        RatesViewModule::class
    ]
)
@RatesScope
interface RatesSubComponent : AndroidInjector<RatesActivity> {

    @Subcomponent.Factory
    interface Builder : AndroidInjector.Factory<RatesActivity>

    fun inject(injectable: RateViewHolder)

}
