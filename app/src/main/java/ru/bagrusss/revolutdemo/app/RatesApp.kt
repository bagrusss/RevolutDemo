package ru.bagrusss.revolutdemo.app

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import ru.bagrusss.revolutdemo.di.DaggerAppComponent


/**
 * Created by bagrusss on 13.08.2019
 */
class RatesApp: DaggerApplication() {

    private val injector by lazy {
        DaggerAppComponent.builder()
                          .application(this)
                          .build()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = injector

}