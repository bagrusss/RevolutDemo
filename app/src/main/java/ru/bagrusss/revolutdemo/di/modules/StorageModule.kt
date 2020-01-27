package ru.bagrusss.revolutdemo.di.modules

import dagger.Binds
import dagger.Module
import ru.bagrusss.revolutdemo.storage.RatesCostStorage
import ru.bagrusss.revolutdemo.storage.RatesCostStorageImMemoryImpl

/**
 * Created by bagrusss on 28.01.2020
 */
@Module
interface StorageModule {

    @Binds
    fun bindRatesStorage(impl: RatesCostStorageImMemoryImpl): RatesCostStorage

}