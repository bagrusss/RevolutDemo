package ru.bagrusss.revolutdemo.util.format

/**
 * Created by bagrusss on 09.01.2020
 */

val Double.formattedMoney: String
    get() {
        val format = if (this % 1 == 0.0) {
            "%.0f"
        } else {
            "%.2f"
        }
        return String.format(format, this)
    }
