package ru.bagrusss.revolutdemo.screens.rates

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable
import ru.bagrusss.revolutdemo.mvvm.BaseViewModel
import ru.bagrusss.revolutdemo.screens.rates.di.RatesScope
import ru.bagrusss.revolutdemo.screens.rates.models.Rate
import ru.bagrusss.revolutdemo.util.rx.plusAssign
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by bagrusss on 13.08.2019
 */
@RatesScope
class RatesVM @Inject constructor(
    interactor: RatesInteractor
) : BaseViewModel<RatesInteractor>(interactor) {

    @JvmField val showLoader = ObservableBoolean(true)
    @JvmField val ratesChanges = MutableLiveData<List<Rate>>()
    @JvmField val mainRateChanged = MutableLiveData<Unit>()
    @JvmField val errors = MutableLiveData<Boolean>()

    private val ratesDisposables = CompositeDisposable()
    private var mainRateUpdated = false

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun started() {
        ratesChanges()
        ratesDisposables += interactor.rateChange.subscribe()
        ratesDisposables += interactor.ratesErrors.subscribe(errors::postValue)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stopped() {
        ratesDisposables.clear()
    }

    fun currentRateCostChanged(rate: String, costText: String) {
        mainRateUpdated = true
        interactor.rateChanged(rate, costText)
    }

    fun listUpdated() {
        if (mainRateUpdated) {
            mainRateChanged.postValue(Unit)
            mainRateUpdated = false
        }
    }

    private fun ratesChanges() {
        ratesDisposables += interactor.ratesUpdates
            .doOnSubscribe {
                if (ratesChanges.value == null)
                    showLoader.set(true)
            }
            .doOnNext { showLoader.set(false) }
            .subscribe(ratesChanges::postValue, Timber::e)
    }

}