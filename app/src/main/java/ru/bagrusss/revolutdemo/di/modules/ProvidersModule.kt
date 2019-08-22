package ru.bagrusss.revolutdemo.di.modules

import dagger.Binds
import dagger.Module
import ru.bagrusss.revolutdemo.providers.ResourcesProvider
import ru.bagrusss.revolutdemo.providers.SchedulersProvider
import ru.bagrusss.revolutdemo.providers.impl.ResourcesProviderImpl
import ru.bagrusss.revolutdemo.providers.impl.SchedulersProviderImpl
import javax.inject.Singleton

/**
 * Created by bagrusss on 13.08.2019
 */
@Module
interface ProvidersModule {

    @Binds
    @Singleton
    fun bindSchedulers(impl: SchedulersProviderImpl): SchedulersProvider

    @Binds
    @Singleton
    fun bindResProvider(impl: ResourcesProviderImpl): ResourcesProvider

}