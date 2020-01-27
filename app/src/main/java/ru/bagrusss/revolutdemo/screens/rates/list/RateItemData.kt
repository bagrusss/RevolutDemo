package ru.bagrusss.revolutdemo.screens.rates.list

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import java.math.BigDecimal

/**
 * Created by bagrusss on 23.08.2019
 */

class RateItemData {
    @JvmField val title = ObservableField("")
    @JvmField val description = ObservableField("")
    @JvmField val imgSrc = ObservableField("")
    @JvmField val cost = ObservableField(BigDecimal.ZERO)
    @JvmField val costActive = ObservableBoolean(false)
}