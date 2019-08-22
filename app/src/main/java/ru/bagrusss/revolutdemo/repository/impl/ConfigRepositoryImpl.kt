package ru.bagrusss.revolutdemo.repository.impl

import android.content.Context
import ru.bagrusss.revolutdemo.providers.ResourcesProvider
import ru.bagrusss.revolutdemo.rates.models.Rate
import ru.bagrusss.revolutdemo.repository.ConfigRepository
import javax.inject.Inject

/**
 * Created by bagrusss on 13.08.2019
 */
class ConfigRepositoryImpl @Inject constructor(
    context: Context,
    private val resRepo: ResourcesProvider
) : ConfigRepository {

    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    private lateinit var cachedRate: Rate

    override var currentRate: Rate
        get() {
            return if (!::cachedRate.isInitialized) {
                prefs.run {
                    val title = getString(TITLE_KEY, null)
                    val rate = if (title == null) {
                        val (description, img) = resRepo.rateImageAndDescription(DEFAULT_TITLE)
                        Rate(
                            title = DEFAULT_TITLE,
                            cost = DEFAULT_COST,
                            description = description,
                            imgUrl = img
                        )
                    } else {
                        Rate(
                            title = title,
                            description = getString(DESCRIPTION_KEY, "")!!,
                            cost = getFloat(COST_KEY, 0f),
                            imgUrl = getString(IMG_KEY, "")!!
                        )
                    }
                    currentRate = rate
                    rate
                }
            } else {
                cachedRate
            }
        }
        set(value) {
            cachedRate = value
            prefs.edit()
                .putString(TITLE_KEY, value.title)
                .putString(DESCRIPTION_KEY, value.description)
                .putFloat(COST_KEY, value.cost)
                .putString(IMG_KEY, value.imgUrl)
                .apply()
        }

    companion object {
        private const val TITLE_KEY = "title"
        private const val DESCRIPTION_KEY = "description"
        private const val IMG_KEY = "img"
        private const val COST_KEY = "cost"

        private const val DEFAULT_TITLE = "EUR"
        private const val DEFAULT_COST = 100f

        private const val PREFS_NAME = "rates"
    }

}