package ru.bagrusss.revolutdemo.screens.rates

import android.content.Context
import android.widget.EditText
import ru.bagrusss.revolutdemo.util.android.currentLocale
import ru.bagrusss.revolutdemo.util.format.RateFormatter
import ru.bagrusss.revolutdemo.util.text.SimpleTextWatcher
import java.lang.ref.WeakReference
import java.text.DecimalFormatSymbols
import kotlin.math.min

/**
 * Created by bagrusss on 09.01.2020
 */
class RateEditWatcher(
    context: Context,
    editText: EditText,
    private val onValueChanged: (String) -> Unit
) : SimpleTextWatcher() {

    private val editTextReference = WeakReference(editText)
    private var oldText = ""

    private val formatter = RateFormatter(
        context.currentLocale
            .let(DecimalFormatSymbols::getInstance)
            .decimalSeparator
    )

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        oldText = s.toString()
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        val editText = editTextReference.get()
        if (editText != null) {
            editText.removeTextChangedListener(this)

            val formatted = formatter.format(s.toString())

            val selection = if (formatted == "0") {
                1
            } else {
                min(editText.selectionStart, formatted.length)
            }

            editText.setText(formatted)
            editText.setSelection(selection)

            if (oldText != formatted) {
                onValueChanged(formatted)
            }

            editText.addTextChangedListener(this)
        }
    }

}