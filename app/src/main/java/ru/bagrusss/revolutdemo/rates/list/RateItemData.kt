package ru.bagrusss.revolutdemo.rates.list

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField

/**
 * Created by bagrusss on 23.08.2019
 */

class RateItemData {
    @JvmField val title = ObservableField("")
    @JvmField val description = ObservableField("")
    @JvmField val cost = ObservableField("")
    @JvmField val imgSrc = ObservableField("")
    @JvmField val inputActive = ObservableBoolean()
}