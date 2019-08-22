package ru.bagrusss.revolutdemo.rates.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.bagrusss.revolutdemo.databinding.ItemRateBinding
import ru.bagrusss.revolutdemo.rates.RatesVM
import ru.bagrusss.revolutdemo.rates.models.Rate
import java.util.*
import javax.inject.Inject
import androidx.recyclerview.widget.DiffUtil



/**
 * Created by bagrusss on 13.08.2019
 */
class RatesAdapter @Inject constructor(private val vm: RatesVM): RecyclerView.Adapter<RateViewHolder>() {

    private val rates = mutableListOf<Rate>()

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
        val callback = RatesDiffCallback(rates, newRates)
        val diffResult = DiffUtil.calculateDiff(callback)
        rates.clear()
        rates.addAll(newRates)
        diffResult.dispatchUpdatesTo(this)
    }

    fun moveItem(position: Int) {
        Collections.swap(rates, position, 0)
        notifyItemMoved(position, 0)
    }

}