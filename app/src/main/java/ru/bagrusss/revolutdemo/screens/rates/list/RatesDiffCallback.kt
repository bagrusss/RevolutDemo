package ru.bagrusss.revolutdemo.screens.rates.list

import androidx.recyclerview.widget.DiffUtil
import ru.bagrusss.revolutdemo.screens.rates.models.Rate

/**
 * Created by bagrusss on 23.08.2019
 */
class RatesDiffCallback : DiffUtil.ItemCallback<Rate>() {

    override fun areItemsTheSame(oldItem: Rate, newItem: Rate) = oldItem.title == newItem.title

    override fun areContentsTheSame(oldItem: Rate, newItem: Rate) = oldItem == newItem

    override fun getChangePayload(oldItem: Rate, newItem: Rate): Any? {
        return if (oldItem != newItem)
            newItem
        else null
    }

}