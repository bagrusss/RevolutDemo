package ru.bagrusss.revolutdemo.rates

import android.content.Context
import android.widget.EditText
import ru.bagrusss.revolutdemo.util.format.RateFormatter
import ru.bagrusss.revolutdemo.util.text.SimpleTextWatcher
import java.lang.ref.WeakReference
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

    private val formatter = RateFormatter(context)

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        oldText = s.toString()
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        val editText = editTextReference.get()
        if (editText != null) {
            editText.removeTextChangedListener(this)

            val formatted = formatter.format(s.toString())

            if (oldText != formatted) {
                val selection = if (formatted == "0") {
                    1
                } else {
                    min(editText.selectionStart, formatted.length)
                }

                editText.setText(formatted)
                editText.setSelection(selection)
                onValueChanged(formatted)
            }

            editText.addTextChangedListener(this)
        }
    }

    /*override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        val editText = editTextReference.get()
        if (holder.adapterPosition == 0 && editText != null) {
            val newText = s.toString()
            if (oldText != newText) {
                editText.removeTextChangedListener(this)

                if (newText == ".") {
                    editText.run {
                        setText("0")
                        setSelection(1)
                        addTextChangedListener(this@RateEditWatcher)
                    }
                    return
                }

                val separator = newText.indexOf(separator)
                val diff = newText.length - separator
                var cursorPosition = editText.selectionStart
                if (separator != -1 && diff >= 4) {
                    cursorPosition = min(editText.selectionStart, oldText.length)
                    editText.setText(oldText)
                    editText.setSelection(cursorPosition)
                }

                val currentText = editText.text.toString()
                val len = currentText.length
                var zeros = 0
                while (zeros < len && currentText.isZero(zeros)) {
                    ++zeros
                }
                when {
                    zeros > 1 -> {
                        val selection = if (zeros != len) {
                            val formatted = currentText.substring(zeros, len)
                            val newSelection = min(--cursorPosition, formatted.length)
                            editText.setText(formatted)
                            newSelection
                        } else {
                            editText.setText("0")
                            1
                        }
                        editText.setSelection(selection)
                    }
                    zeros == 1 -> {

                    }
                }

                editText.addTextChangedListener(this)

                onRateValueChanged(newText)
            }
        }
    }*/


    // private fun String.isZero(position: Int) = this[position] == '0'

}