package ru.bagrusss.revolutdemo.rates.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.bagrusss.revolutdemo.databinding.ItemRateBinding
import ru.bagrusss.revolutdemo.rates.RatesVM
import ru.bagrusss.revolutdemo.rates.models.Rate
import java.util.*
import javax.inject.Inject

/**
 * Created by bagrusss on 13.08.2019
 */
class RatesAdapter @Inject constructor(private val vm: RatesVM): RecyclerView.Adapter<RateViewHolder>() {

    private var rates = listOf<Rate>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RateViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRateBinding.inflate(inflater, parent, false)
        return RateViewHolder(binding, vm)
    }

    override fun onBindViewHolder(holder: RateViewHolder, position: Int) {
        val rate = rates[position]
        holder.onBind(rate)
    }

    override fun getItemCount() = rates.size

    fun swap(newRates: List<Rate>) {
        if (rates.isEmpty()) {
            rates = newRates
            notifyDataSetChanged()
        } else {
            rates = newRates
            notifyItemRangeChanged(1, newRates.size - 1)
        }
    }

    fun moveItem(position: Int) {
        Collections.swap(rates, position, 0)
        notifyItemMoved(position, 0)
    }

}