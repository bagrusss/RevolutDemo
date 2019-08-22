package ru.bagrusss.revolutdemo.mvvm

import androidx.annotation.CallSuper
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by bagrusss on 12.08.2019
 */
abstract class BaseViewModel<I : Interactor>(@JvmField protected val interactor: I) : ViewModel(), LifecycleObserver {

    protected val disposables by lazy(::CompositeDisposable)

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    open fun created() {}

    @CallSuper
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    open fun destroyed() {
        disposables.clear()
    }

}