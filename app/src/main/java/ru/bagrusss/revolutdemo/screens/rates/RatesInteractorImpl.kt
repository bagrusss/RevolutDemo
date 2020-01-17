package ru.bagrusss.revolutdemo.screens.rates

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import ru.bagrusss.revolutdemo.providers.ResourcesProvider
import ru.bagrusss.revolutdemo.providers.SchedulersProvider
import ru.bagrusss.revolutdemo.repository.RatesRepository
import ru.bagrusss.revolutdemo.screens.rates.models.Rate
import java.math.BigDecimal
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

    private val rateChangePublisher = PublishSubject.create<Pair<String, String>>()

    override val ratesUpdates: Observable<List<Rate>> by lazy {
        Observable.interval(1, TimeUnit.SECONDS)
            .startWith(0)
            .flatMap { ratesRepo.actualRates }
            .mergeWith(ratesRepo.costChanges)
            .map { ratesCost ->
                val (baseTitle, currentCost) = ratesRepo.run { currentBaseRate to currentCost }
                val (baseDescription, baseImg) = resourcesProvider.rateDescriptionAndImage(baseTitle)
                val rates = mutableListOf(
                    Rate(
                        title = baseTitle,
                        description = baseDescription,
                        imgUrl = baseImg,
                        cost = currentCost
                    )
                )

                for (i in 1 until ratesCost.size) {
                    val (title, cost) = ratesCost[i]
                    val (description, img) = resourcesProvider.rateDescriptionAndImage(title)
                    rates.add(
                        Rate(
                            title = title,
                            description = description,
                            imgUrl = img,
                            cost = (cost.toBigDecimal() * currentCost)
                        )
                    )
                }
                rates as List<Rate>
            }
            .observeOn(schedulers.ui)
    }

    override val rateChange: Completable by lazy {
        rateChangePublisher
            .hide()
            .observeOn(schedulers.computation)
            .doOnNext { (rate, cost) ->
                val newCost = try {
                    cost.toBigDecimal()
                } catch (e: NumberFormatException) {
                    BigDecimal.ZERO
                }
                ratesRepo.run {
                    currentBaseRate = rate
                    currentCost = newCost
                }
            }
            .ignoreElements()
    }

    override fun rateChanged(rate: String, cost: String) = rateChangePublisher.onNext(rate to cost)

}
