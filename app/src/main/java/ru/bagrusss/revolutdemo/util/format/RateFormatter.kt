package ru.bagrusss.revolutdemo.util.format

/**
 * Created by bagrusss on 14.01.2020
 */
class RateFormatter(
    @JvmField val separator: Char
) {

    fun format(text: String): String {
        val textLen = text.length
        if (textLen == 0) {
            return "0"
        }

        var zeros = 0
        while (zeros < textLen && text[zeros] == '0') {
            ++zeros
        }
        val separatorPosition = text.indexOf(separator)

        val digitsAfterSeparator = textLen - 1 - separatorPosition
        return if (separatorPosition == 0) {
            "0$text"
        } else {
            if (textLen == 1) {
                text
            } else {
                if (zeros < textLen && text[zeros] == '.') {
                    --zeros
                }
                when (digitsAfterSeparator) {
                    0, 1, 2, textLen -> text.substring(zeros, textLen)
                    else -> text.substring(zeros, separatorPosition + 3)
                }
            }
        }
    }

}