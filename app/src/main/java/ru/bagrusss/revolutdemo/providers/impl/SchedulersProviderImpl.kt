package ru.bagrusss.revolutdemo.providers.impl

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import ru.bagrusss.revolutdemo.providers.SchedulersProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * Created by bagrusss on 13.08.2019
 */
class SchedulersProviderImpl @Inject constructor(): SchedulersProvider {

    override val io: Scheduler
        get() = Schedulers.io()

    override val computation: Scheduler
        get() = Schedulers.computation()

    override val ui: Scheduler
        get() = AndroidSchedulers.mainThread()

}