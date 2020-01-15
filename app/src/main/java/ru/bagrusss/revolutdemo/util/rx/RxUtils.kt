package ru.bagrusss.revolutdemo.util.rx

import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by bagrusss on 14.01.2020
 */

fun <T> single(func: () -> T) = Single.fromCallable(func)

fun <T> maybe(func: () -> T) = Maybe.fromCallable(func)

fun <T> observable(func: () -> T) = Observable.fromCallable(func)

operator fun CompositeDisposable.plusAssign(d: Disposable) {
    add(d)
}