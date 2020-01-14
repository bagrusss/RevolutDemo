package ru.bagrusss.revolutdemo.di

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import ru.bagrusss.revolutdemo.app.RatesApp
import ru.bagrusss.revolutdemo.di.modules.*
import ru.bagrusss.revolutdemo.screens.rates.di.RatesInjectModule
import javax.inject.Singleton


/**
 * Created by bagrusss on 13.08.2019
 */
@Component(
    modules = [
        AndroidModule::class,

        NetModule::class,

        ProvidersModule::class,
        ReposModule::class,
        MappersModule::class,

        AndroidInjectionModule::class,
        RatesInjectModule::class
    ]
)
@Singleton
interface AppComponent : AndroidInjector<RatesApp> {

    @Component.Factory
    interface Factory : AndroidInjector.Factory<RatesApp>

}