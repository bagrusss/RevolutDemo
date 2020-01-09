package ru.bagrusss.revolutdemo.rates

import io.reactivex.Completable
import io.reactivex.Observable
import ru.bagrusss.revolutdemo.providers.ResourcesProvider
import ru.bagrusss.revolutdemo.repository.RatesRepository
import ru.bagrusss.revolutdemo.providers.SchedulersProvider
import ru.bagrusss.revolutdemo.rates.models.Rate
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by bagrusss on 13.08.2019
 */
class RatesInteractorImpl @Inject constructor(
    private val ratesRepo: RatesRepository,
    private val resourcesProvider: ResourcesProvider,
    private val schedulers: SchedulersProvider
) : RatesInteractor {

    override val ratesChanges: Observable<List<Rate>> by lazy {
        Observable.interval(1, TimeUnit.SECONDS)
                  .startWith(0)
                  .switchMapSingle { ratesRepo.actualRates }
                  .mergeWith(ratesRepo.currentCostChanges)
                  .map { rates ->
                      val (baseRate, baseCost) = ratesRepo.currentBaseRate
                      val (baseDescription, baseImg) = resourcesProvider.rateImageAndDescription(baseRate)
                      val baseRateItem = Rate(
                          title = baseRate,
                          description = baseDescription,
                          imgUrl = baseImg,
                          cost = baseCost
                      )
                      val newRates = rates.map {
                          val finalCost = it.cost * baseCost
                          it.copy(cost = finalCost)
                      }
                      listOf(baseRateItem) + newRates
                  }
                  .observeOn(schedulers.ui)
    }

    override fun rateChanged(rate: String, cost: String) = Completable.fromAction {
        if (cost.isNotEmpty()) {
            val newCost = cost.toDouble()
            if (newCost != ratesRepo.currentBaseRate.second) {
                ratesRepo.currentBaseRate = rate to cost.toDouble()
            }
        } else {
            ratesRepo.currentBaseRate = rate to 0.0
        }
    }
    .subscribeOn(schedulers.io)
    .observeOn(schedulers.ui)

}
