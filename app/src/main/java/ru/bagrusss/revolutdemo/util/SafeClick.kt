package ru.bagrusss.revolutdemo.util

import android.view.View

/**
 * Created by bagrusss on 23.01.2020
 */

fun safeClick(click: (View) -> Unit) = View.OnClickListener { v ->
    v.isClickable = false
    click(v)
    v.isClickable = true
}