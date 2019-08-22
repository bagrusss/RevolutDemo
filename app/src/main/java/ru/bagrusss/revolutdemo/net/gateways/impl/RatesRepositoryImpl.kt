package ru.bagrusss.revolutdemo.net.gateways.impl

import io.reactivex.Single
import ru.bagrusss.revolutdemo.net.api.RatesService
import ru.bagrusss.revolutdemo.net.gateways.Gateway
import ru.bagrusss.revolutdemo.net.gateways.RatesRepository
import ru.bagrusss.revolutdemo.providers.ResourcesProvider
import ru.bagrusss.revolutdemo.rates.models.Rate
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject
import kotlin.math.round

/**
 * Created by bagrusss on 13.08.2019
 */
class RatesRepositoryImpl @Inject constructor(
    ratesService: RatesService,
    private val resourcesProvider: ResourcesProvider
) : Gateway<RatesService>(ratesService), RatesRepository {

    override fun actualRates(baseRate: String, baseCost: Float): Single<List<Rate>> {
        return service.getRates(baseRate)
                      .map {
                          it.rates.map { (rate, cost) ->
                              val (description, img) = resourcesProvider.rateImageAndDescription(rate)
                              val finalCost = round(cost * baseCost * 100) / 100
                              Rate(
                                  title = rate,
                                  description = description,
                                  imgUrl = img,
                                  cost = finalCost
                              )
                          }
                      }
    }

}