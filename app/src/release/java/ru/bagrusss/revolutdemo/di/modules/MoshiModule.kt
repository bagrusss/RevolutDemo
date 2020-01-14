package ru.bagrusss.revolutdemo.di.modules

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by bagrusss on 14.01.2020
 */
@Module
class MoshiModule {

    @Provides
    @Singleton
    fun provideMoshi() = Moshi.Builder()
        .build()

}