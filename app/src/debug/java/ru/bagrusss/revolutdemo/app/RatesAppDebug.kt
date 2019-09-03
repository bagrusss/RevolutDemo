package ru.bagrusss.revolutdemo.app

import com.facebook.stetho.Stetho
import timber.log.Timber


/**
 * Created by bagrusss on 13.08.2019
 */
class RatesAppDebug: RatesApp() {

    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)
        Timber.plant(Timber.DebugTree())
    }

}