package ru.bagrusss.revolutdemo.di

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import ru.bagrusss.revolutdemo.app.RatesApp
import ru.bagrusss.revolutdemo.di.modules.AndroidModule
import ru.bagrusss.revolutdemo.di.modules.GatewaysModule
import ru.bagrusss.revolutdemo.di.modules.NetModule
import ru.bagrusss.revolutdemo.di.modules.ProvidersModule
import ru.bagrusss.revolutdemo.rates.di.RatesInjectModule
import javax.inject.Singleton


/**
 * Created by bagrusss on 13.08.2019
 */
@Component(
    modules = [
        NetModule::class,
        GatewaysModule::class,

        AndroidModule::class,

        ProvidersModule::class,

        AndroidInjectionModule::class,
        RatesInjectModule::class
    ]
)
@Singleton
interface AppComponent: AndroidInjector<RatesApp> {

    @Component.Factory
    interface Factory: AndroidInjector.Factory<RatesApp>

}