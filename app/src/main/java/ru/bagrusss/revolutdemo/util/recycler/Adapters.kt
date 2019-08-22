package ru.bagrusss.revolutdemo.util.recycler

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import ru.bagrusss.revolutdemo.glide.GlideApp

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