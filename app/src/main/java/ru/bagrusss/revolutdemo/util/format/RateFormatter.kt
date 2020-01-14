package ru.bagrusss.revolutdemo.util.format

import android.content.Context
import androidx.core.os.ConfigurationCompat
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

/**
 * Created by bagrusss on 14.01.2020
 */
class RateFormatter(
    context: Context
) {

    @JvmField
    val separator: String

    init {
        val locale = ConfigurationCompat.getLocales(context.resources.configuration).get(0)
        separator = DecimalFormatSymbols.getInstance(locale).decimalSeparator.toString()
    }

    private val formatter2 = DecimalFormat("0$separator##").apply {
        roundingMode = RoundingMode.HALF_UP
    }
    private val formatter1 = DecimalFormat("0$separator#").apply {
        roundingMode = RoundingMode.HALF_UP
    }
    private val formatter0 = DecimalFormat("0$separator").apply {
        roundingMode = RoundingMode.HALF_UP
    }

    fun format(text: String): String {
        val pointPosition = text.indexOf(separator)
        val doubleValue = try {
            text.toDouble()
        } catch (e: NumberFormatException) {
            0.0
        }
        val newLen = text.length
        return when (newLen - 1 - pointPosition) {
            text.length -> formatter2.format(doubleValue)
            0 -> formatter0.format(doubleValue)
            1 -> formatter1.format(doubleValue).run {
                if (!contains(separator))
                    this + separator + text.last()
                else this
            }
            else -> formatter2.format(doubleValue).run {
                if (!contains(separator))
                    this + separator + text.substring(pointPosition + 1, newLen)
                else this
            }
        }
    }

}