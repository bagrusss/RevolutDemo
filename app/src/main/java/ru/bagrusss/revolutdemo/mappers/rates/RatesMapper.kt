package ru.bagrusss.revolutdemo.mappers.rates

import ru.bagrusss.revolutdemo.mappers.Mapper
import ru.bagrusss.revolutdemo.providers.ResourcesProvider
import ru.bagrusss.revolutdemo.rates.models.Rate
import javax.inject.Inject

/**
 * Created by bagrusss on 23.09.2019
 */
class RatesMapper @Inject constructor(private val resourcesProvider: ResourcesProvider): Mapper<Map<String, Double>, List<Rate>> {

    override fun map(from: Map<String, Double>): List<Rate> = from.map { (rate, cost) ->
        val (description, img) = resourcesProvider.rateDescriptionAndImage(rate)
        Rate(
            title = rate,
            description = description,
            imgUrl = img,
            cost = cost
        )
    }

}