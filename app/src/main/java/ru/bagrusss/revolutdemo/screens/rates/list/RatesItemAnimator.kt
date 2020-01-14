package ru.bagrusss.revolutdemo.screens.rates.list

import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import ru.bagrusss.revolutdemo.screens.rates.RatesVM

/**
 * Created by bagrusss on 23.08.2019
 */
class RatesItemAnimator(private val vm: RatesVM): DefaultItemAnimator() {

    init {
        supportsChangeAnimations = false
    }

    override fun onAnimationFinished(viewHolder: RecyclerView.ViewHolder) {
        if (viewHolder.oldPosition != 0 && viewHolder.adapterPosition == 0) {
            vm.ratesAnimationsEnded()
        }
    }
}