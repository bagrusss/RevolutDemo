package ru.bagrusss.revolutdemo.util.databinding

import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import ru.bagrusss.revolutdemo.glide.GlideApp
import kotlin.math.min

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
fun EditText.formatCost(cost: Double) {
    val current = try {
        text.toString().toDouble()
    } catch (e: NumberFormatException) {
        0.0
    }
    if (current != cost) {
        val costString = cost.toString()
        val selection = min(selectionStart, costString.length)
        setText(costString)
        setSelection(selection)
    }
}