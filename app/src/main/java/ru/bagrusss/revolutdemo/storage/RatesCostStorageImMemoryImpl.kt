package ru.bagrusss.revolutdemo.storage

import ru.bagrusss.revolutdemo.screens.rates.models.RateCost
import java.util.*
import javax.inject.Inject

/**
 * Created by bagrusss on 28.01.2020
 */
class RatesCostStorageImMemoryImpl @Inject constructor() : RatesCostStorage {

    private val cachedRates = mutableListOf<RateCost>()

    override var currentRates: List<RateCost>
        get() = cachedRates.toList()
        set(value) = mergeRates(value)

    override fun selectPrimaryRate(title: String) = synchronized(cachedRates) {
        val newRateIndex = cachedRates.indexOfFirst { it.title == title }
        if (newRateIndex != -1) {
            val newRateItem = cachedRates.removeAt(newRateIndex)
            cachedRates.forEach {
                it.cost /= newRateItem.cost
            }
            cachedRates.add(0, newRateItem.apply { cost = 1.0 })
        }
    }

    private fun mergeRates(from: List<RateCost>) = synchronized(cachedRates) {
        from.forEach { newRate ->
            val oldRate = cachedRates.firstOrNull { it.title == newRate.title }
            if (oldRate == null) {
                cachedRates.add(newRate)
            } else {
                oldRate.cost = newRate.cost
            }
        }
        val indexesToRemove = LinkedList<Int>()
        for (i in 1 until cachedRates.size) {
            val oldRate = cachedRates[i]
            val newRateIndex = from.indexOfFirst { it.title == oldRate.title }
            if (newRateIndex == -1) {
                indexesToRemove.add(i)
            }
        }
        indexesToRemove.forEach {
            cachedRates.removeAt(it)
        }
    }


}