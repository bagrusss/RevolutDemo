package ru.bagrusss.revolutdemo.rates.list

import androidx.recyclerview.widget.DiffUtil
import ru.bagrusss.revolutdemo.rates.models.Rate

/**
 * Created by bagrusss on 23.08.2019
 */
class RatesDiffCallback(private val oldCollection: List<Rate>,
                        private val newCollection: List<Rate>): DiffUtil.Callback() {

    override fun getOldListSize() = oldCollection.size
    override fun getNewListSize() = newCollection.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldCollection[oldItemPosition].title == newCollection[newItemPosition].title
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldContent = oldCollection[oldItemPosition]
        val newContent = newCollection[newItemPosition]
        return oldContent.title == newContent.title
                && oldContent.cost == newContent.cost
    }

}