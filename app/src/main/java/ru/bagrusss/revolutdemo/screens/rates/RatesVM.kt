package ru.bagrusss.revolutdemo.screens.rates

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import ru.bagrusss.revolutdemo.mvvm.BaseViewModel
import ru.bagrusss.revolutdemo.screens.rates.di.RatesScope
import ru.bagrusss.revolutdemo.screens.rates.models.Rate
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by bagrusss on 13.08.2019
 */
@RatesScope
class RatesVM @Inject constructor(
    interactor: RatesInteractor
) : BaseViewModel<RatesInteractor>(interactor) {

    private var animationsEnd = true

    @JvmField val showLoader = ObservableBoolean(true)
    @JvmField val ratesChanges = MutableLiveData<List<Rate>>()
    @JvmField val errorEvent = MutableLiveData<Unit>()
    @JvmField val ratesChanged = MutableLiveData<Int>()

    private val ratesDisposable = CompositeDisposable()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun started() = ratesChanges()

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stopped() {
        ratesDisposable.clear()
    }

    fun ratesAnimationsEnded() {
        animationsEnd = true
    }

    fun ratesChanges() {
        ratesDisposable += interactor.ratesUpdates
            .doOnSubscribe { showLoader.set(true) }
            .filter { animationsEnd }
            .doOnNext { showLoader.set(false) }
            .subscribe(ratesChanges::postValue) {
                errorEvent.postValue(Unit)
                Timber.e(it)
                showLoader.set(false)
                ratesDisposable.clear()
            }
        ratesDisposable += interactor.rateChange
            .subscribe()
    }

    fun ratesClicked(position: Int, rate: String, costText: String) {
        if (animationsEnd) {
            animationsEnd = false
            currentRateCostChanged(rate, costText)
            ratesChanged.postValue(position)
        }
    }

    fun currentRateCostChanged(rate: String, costText: String) {
        interactor.rateChanged(rate, costText)
    }

}