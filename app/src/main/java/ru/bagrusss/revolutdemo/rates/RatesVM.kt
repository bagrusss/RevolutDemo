package ru.bagrusss.revolutdemo.rates

import ru.bagrusss.revolutdemo.mvvm.BaseViewModel
import ru.bagrusss.revolutdemo.rates.di.RatesScope
import javax.inject.Inject

/**
 * Created by bagrusss on 13.08.2019
 */
@RatesScope
class RatesVM @Inject constructor(interactor: RatesInteractor): BaseViewModel<RatesInteractor>(interactor) {

}