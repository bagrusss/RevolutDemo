package ru.bagrusss.revolutdemo.providers

import io.reactivex.Scheduler

/**
 * Created by bagrusss on 13.08.2019
 */
interface SchedulersProvider {
    val io: Scheduler
    val computation: Scheduler
    val ui: Scheduler
}