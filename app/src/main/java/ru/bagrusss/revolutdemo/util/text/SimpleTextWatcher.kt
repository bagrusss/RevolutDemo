package ru.bagrusss.revolutdemo.util.text

import android.text.Editable
import android.text.TextWatcher

/**
 * Created by bagrusss on 23.09.2019
 */
abstract class SimpleTextWatcher: TextWatcher {

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(s: Editable) {}

}