package ru.bagrusss.revolutdemo.util.databinding

import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import ru.bagrusss.revolutdemo.glide.GlideApp
import ru.bagrusss.revolutdemo.util.format.preFormattedMoney
import java.math.BigDecimal

/**
 * Created by bagrusss on 23.08.2019
 */

@BindingAdapter("img")
fun ImageView.loadImage(img: String?) {
    GlideApp.with(this)
        .load(img)
        .centerCrop()
        .into(this)
}

@BindingAdapter("rate_cost")
fun EditText.formatCost(cost: BigDecimal?) {
    cost?.let {
        val current = try {
            text.toString().toBigDecimal()
        } catch (e: NumberFormatException) {
            BigDecimal.ZERO
        }
        if (current != cost)
            setText(cost.preFormattedMoney)
    }
}