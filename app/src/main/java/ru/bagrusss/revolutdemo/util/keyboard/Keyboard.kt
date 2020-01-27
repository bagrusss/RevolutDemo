package ru.bagrusss.revolutdemo.util.keyboard

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager


/**
 * Created by bagrusss on 23.01.2020
 */

fun View.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, 0)
}