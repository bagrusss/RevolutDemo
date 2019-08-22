package ru.bagrusss.revolutdemo.repository.impl

import android.content.Context
import ru.bagrusss.revolutdemo.repository.ConfigRepository
import javax.inject.Inject

/**
 * Created by bagrusss on 13.08.2019
 */
class ConfigRepositoryImpl @Inject constructor(context: Context): ConfigRepository {

    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    override var currentRate: String
        get() = prefs.getString(CURRENT_KEY, DEFAULT_RATE) ?: DEFAULT_RATE
        set(value) {
            prefs.edit()
                 .putString(CURRENT_KEY, value)
                 .apply()
        }

    companion object {
        private const val CURRENT_KEY = "current"
        private const val DEFAULT_RATE = "EUR"
        private const val PREFS_NAME = "rates"
    }

}