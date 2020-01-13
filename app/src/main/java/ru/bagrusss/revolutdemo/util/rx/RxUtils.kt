package ru.bagrusss.revolutdemo.util.rx

import io.reactivex.Single

/**
 * Created by bagrusss on 14.01.2020
 */

fun <T> single(func: () -> T) = Single.fromCallable(func)