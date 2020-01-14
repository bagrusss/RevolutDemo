package ru.bagrusss.revolutdemo.util.format

/**
 * Created by bagrusss on 09.01.2020
 */

val Double.preFormattedMoney: String
    get() {
        return if (this == 0.0) {
            "0"
        } else {
            this.toString()
        }
    }
