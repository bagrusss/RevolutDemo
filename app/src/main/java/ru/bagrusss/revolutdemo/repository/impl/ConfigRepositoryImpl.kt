package ru.bagrusss.revolutdemo.repository.impl

import ru.bagrusss.revolutdemo.repository.ConfigRepository
import javax.inject.Inject

/**
 * Created by bagrusss on 13.08.2019
 */
class ConfigRepositoryImpl @Inject constructor() : ConfigRepository {

    private lateinit var cachedBaseRate: Pair<String, Float>

    override var currentBaseRate: Pair<String, Float>
        get() {
            if (!::cachedBaseRate.isInitialized) {
                cachedBaseRate = DEFAULT_TITLE to DEFAULT_COST
            }
            return cachedBaseRate

        }
        set(value) {
            cachedBaseRate = value
        }

    companion object {
        private const val DEFAULT_TITLE = "EUR"
        private const val DEFAULT_COST = 100f
    }

}