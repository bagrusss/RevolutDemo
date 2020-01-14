package ru.bagrusss.revolutdemo.screens.rates.list

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.bagrusss.revolutdemo.databinding.ItemRateBinding
import ru.bagrusss.revolutdemo.screens.rates.RatesVM
import ru.bagrusss.revolutdemo.screens.rates.models.Rate
import ru.bagrusss.revolutdemo.util.recycler.DataAdapter
import javax.inject.Inject


/**
 * Created by bagrusss on 13.08.2019
 */
class RatesAdapter @Inject constructor(
    private val vm: RatesVM
) : DataAdapter<Rate, RateViewHolder>() {

    override val items = mutableListOf<Rate>()

    override fun provideDiffUtilsCallback(newItems: List<Rate>) = RatesDiffCallback(items, newItems)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RateViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRateBinding.inflate(inflater, parent, false)
        return RateViewHolder(binding, vm)
    }

    fun moveItem(position: Int) {
        notifyItemMoved(position, 0)
        val newRateItem = items.removeAt(position)
        items.add(0, newRateItem)
    }


}