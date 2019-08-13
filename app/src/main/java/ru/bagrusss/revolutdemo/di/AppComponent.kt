package ru.bagrusss.revolutdemo.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import ru.bagrusss.revolutdemo.app.RatesApp
import ru.bagrusss.revolutdemo.di.modules.GatewaysModule
import ru.bagrusss.revolutdemo.di.modules.NetModule
import ru.bagrusss.revolutdemo.rates.di.RatesInjectModule
import javax.inject.Singleton


/**
 * Created by bagrusss on 13.08.2019
 */
@Component(
    modules = [
        NetModule::class,
        GatewaysModule::class,

        AndroidInjectionModule::class,
        RatesInjectModule::class
    ]
)
@Singleton
interface AppComponent: AndroidInjector<RatesApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}