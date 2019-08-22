package ru.bagrusss.revolutdemo.providers.impl

import android.content.Context
import ru.bagrusss.revolutdemo.R
import ru.bagrusss.revolutdemo.providers.ResourcesProvider
import javax.inject.Inject

/**
 * Created by bagrusss on 13.08.2019
 */
class ResourcesProviderImpl @Inject constructor(private val context: Context) : ResourcesProvider {

    private val ratesDescription = context.run {
        mapOf(
            "AUD" to getString(R.string.australian),
            "BGN" to getString(R.string.bulgaria),
            "BRL" to getString(R.string.brazilian),
            "CAD" to getString(R.string.canadian),
            "CHF" to getString(R.string.swiss),
            "CNY" to getString(R.string.chinese),
            "CZK" to getString(R.string.czech),
            "DKK" to getString(R.string.denmark),
            "GBP" to getString(R.string.united_kingdom),
            "HKD" to getString(R.string.hong_kong),
            "HRK" to getString(R.string.croatia),
            "HUF" to getString(R.string.hungary),
            "IDR" to getString(R.string.indonesia),
            "ILS" to getString(R.string.israel),
            "INR" to getString(R.string.india),
            "ISK" to getString(R.string.iceland),
            "JPY" to getString(R.string.japan),
            "KRW" to getString(R.string.south_korea),
            "MXN" to getString(R.string.mexico),
            "MYR" to getString(R.string.malaysia),
            "NOK" to getString(R.string.norway),
            "NZD" to getString(R.string.new_zealand),
            "PHP" to getString(R.string.philippines),
            "PLN" to getString(R.string.poland),
            "RON" to getString(R.string.romania),
            "RUB" to getString(R.string.russia),
            "SEK" to getString(R.string.sweden),
            "SGD" to getString(R.string.singapore),
            "THB" to getString(R.string.thailand),
            "TRY" to getString(R.string.turkey),
            "USD" to getString(R.string.usa),
            "ZAR" to getString(R.string.south_africa),
            "EUR" to getString(R.string.euro)
        )
    }

    override fun rateImageAndDescription(rate: String): Pair<String, String> {
        val description = ratesDescription[rate]
        return if (description != null) {
                   description to imageForRate(rate)
               } else {
                   context.getString(R.string.unknown_rate) to ""
               }
    }

    private fun imageForRate(rate: String) = "$BASE_IMAGE_URL$rate.png"

    companion object {
        const val BASE_IMAGE_URL = "https://github.com/bagrusss/RevolutDemo/raw/master/images/"
    }

}