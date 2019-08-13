package ru.bagrusss.revolutdemo.rates

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
class RatesVM @Inject constructor(interactor: RatesInteractor): BaseViewModel<RatesInteractor>(interactor) {

    @JvmField val ratesChanges = MutableLiveData<List<Rate>>()
    @JvmField val errorEvent = MutableLiveData<Unit>()

    override fun created() {
        ratesChanges()
    }

    fun ratesChanges() {
        disposables += interactor.ratesChanges.subscribe({
            ratesChanges.postValue(it)
        }, {
            errorEvent.postValue(Unit)
            Timber.e(it)
        })
    }

}