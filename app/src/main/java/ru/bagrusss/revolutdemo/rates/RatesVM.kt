package ru.bagrusss.revolutdemo.rates

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.plusAssign
import ru.bagrusss.revolutdemo.mvvm.BaseViewModel
import ru.bagrusss.revolutdemo.rates.di.RatesScope
import ru.bagrusss.revolutdemo.rates.models.Rate
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by bagrusss on 13.08.2019
 */
@RatesScope
class RatesVM @Inject constructor(
    interactor: RatesInteractor
): BaseViewModel<RatesInteractor>(interactor) {

    private var animationsEnd = true

    @JvmField val showLoader = ObservableBoolean(true)

    @JvmField val ratesChanges = MutableLiveData<List<Rate>>()
    @JvmField val errorEvent = MutableLiveData<Unit>()
    @JvmField val ratesChanged = MutableLiveData<Int>()

    override fun created() {
        ratesChanges()
    }

    fun ratesAnimationsEnded() {
        animationsEnd = true
    }

    fun ratesChanges() {
        disposables += interactor.ratesChanges
                                 .doOnSubscribe { showLoader.set(true) }
                                 .filter { animationsEnd }
                                 .doOnNext { showLoader.set(false) }
                                 .subscribe(ratesChanges::postValue) {
                                     errorEvent.postValue(Unit)
                                     Timber.e(it)
                                     showLoader.set(false)
                                 }
    }

    fun ratesClicked(position: Int, rate: String, costText: String) {
        if (animationsEnd) {
            animationsEnd = false
            ratesChanged.postValue(position)
            disposables += interactor.rateChanged(rate, costText)
                                     .subscribe()
        }
    }

    fun currentRateCostChanged(rate: String, costText: String) {
        disposables += interactor.rateChanged(rate, costText)
                                 .subscribe()
    }

}