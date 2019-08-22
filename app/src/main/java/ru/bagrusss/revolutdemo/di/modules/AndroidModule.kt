package ru.bagrusss.revolutdemo.di.modules

import android.content.Context
import dagger.Binds
import dagger.Module
import ru.bagrusss.revolutdemo.app.RatesApp
import javax.inject.Singleton

/**
 * Created by bagrusss on 22.08.2019
 */
@Module
interface AndroidModule {

    @Binds
    @Singleton
    fun bindContext(impl: RatesApp): Context

}