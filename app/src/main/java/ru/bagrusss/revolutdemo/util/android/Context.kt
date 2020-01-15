package ru.bagrusss.revolutdemo.util.android

import android.content.Context
import androidx.core.os.ConfigurationCompat

/**
 * Created by bagrusss on 15.01.2020
 */

val Context.currentLocale
    get() = ConfigurationCompat.getLocales(resources.configuration).get(0)