package ru.bagrusss.revolutdemo.providers

/**
 * Created by bagrusss on 13.08.2019
 */
interface ResourcesProvider {

    fun rateDescriptionAndImage(rate: String): Pair<String, String>

}