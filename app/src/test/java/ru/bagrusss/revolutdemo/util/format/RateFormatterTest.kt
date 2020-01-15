package ru.bagrusss.revolutdemo.util.format

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by bagrusss on 15.01.2020
 */
class RateFormatterTest {

    private val separator = '.'
    private val formatter = RateFormatter(separator)

    @Test
    fun `when zeros on start then clean number without point`() {
        val number = "0001234"

        val formatted = formatter.format(number)

        assertEquals("1234", formatted)
    }

    @Test
    fun `when zeros on start then clean number with point`() {
        val number = "0001234.3"

        val formatted = formatter.format(number)

        assertEquals("1234.3", formatted)
    }


    @Test
    fun `when two digits after point then clean number with point`() {
        val number = "0001234.32"

        val formatted = formatter.format(number)

        assertEquals("1234.32", formatted)
    }

    @Test
    fun `when more two digits after point then clean number with two digits truncated down`() {
        val number1 = "0001234.3267"
        val number2 = "0001234.3247"

        val formatted1 = formatter.format(number1)
        val formatted2 = formatter.format(number2)

        assertEquals("1234.32", formatted1)
        assertEquals("1234.32", formatted2)
    }


    @Test
    fun `when digits with point only then clean formatted string not change`() {
        val number = "1234."

        val formatted = formatter.format(number)

        assertEquals(number, formatted)
    }

    @Test
    fun `when point only then zero with point`() {
        val number = "."

        val formatted = formatter.format(number)

        assertEquals("0.", formatted)
    }

    @Test
    fun `when one zero and number then zero with point`() {
        val number = "01"

        val formatted = formatter.format(number)

        assertEquals("1", formatted)
    }


    @Test
    fun `when one zero after point only then zero with point`() {
        val number = ".0"

        val formatted = formatter.format(number)

        assertEquals("0.0", formatted)
    }

    @Test
    fun `when two zero after point only then zero with point`() {
        val number = ".00"

        val formatted = formatter.format(number)

        assertEquals("0.00", formatted)
    }

    @Test
    fun `when text is empty than 0`() {
        val number = ""

        val formatted = formatter.format(number)

        assertEquals("0", formatted)
    }

    @Test
    fun `when zero and digit is empty than digit`() {
        val number = "01"

        val formatted = formatter.format(number)

        assertEquals("1", formatted)
    }

    @Test
    fun `when zero and zeros after point is empty than digit`() {
        val number = "0.0000"

        val formatted = formatter.format(number)

        assertEquals("0.00", formatted)
    }

}