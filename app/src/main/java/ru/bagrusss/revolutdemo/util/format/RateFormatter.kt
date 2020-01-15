package ru.bagrusss.revolutdemo.util.format

import java.math.RoundingMode
import java.text.DecimalFormat

/**
 * Created by bagrusss on 14.01.2020
 */
class RateFormatter(
    @JvmField val separator: Char
) {

    private val formatter2 = DecimalFormat("0$separator##").apply {
        roundingMode = RoundingMode.DOWN
    }

    private val formatter1 = DecimalFormat("0$separator#").apply {
        roundingMode = RoundingMode.DOWN
    }
    private val formatter0 = DecimalFormat("0$separator").apply {
        roundingMode = RoundingMode.DOWN
    }

    fun format(text: String): String {
        val textLen = text.length
        if (textLen == 0) {
            return text
        }

        val doubleValue = try {
            text.toDouble()
        } catch (e: NumberFormatException) {
            0.0
        }
        var zeros = 0
        while (zeros < textLen && text[zeros] == '0') {
            ++zeros
        }
        val separatorPosition = text.indexOf(separator)
        when (textLen - 1 - separatorPosition) {
            0 -> text.substring(zeros, textLen)

        }
        return when (textLen - 1 - separatorPosition) {
            textLen -> formatter1.format(doubleValue)
            0 -> formatter0.format(doubleValue)
            1 -> formatter1.format(doubleValue).run {
                if (!contains(separator))
                    this + separator + text.last()
                else this
            }
            else -> formatter2.format(doubleValue).run {
                val formattedSeparatorPosition = indexOf(separator)
                if (formattedSeparatorPosition == -1) {
                    this + text.substring(separatorPosition, separatorPosition + 3)
                } else {
                    if (length - formattedSeparatorPosition < textLen - separatorPosition) {
                        val lastPart = text.substring(separatorPosition + 1, separatorPosition + 3)
                        substring(0, formattedSeparatorPosition + 1) + lastPart
                    } else {
                        this
                    }
                }
            }
        }
    }

}