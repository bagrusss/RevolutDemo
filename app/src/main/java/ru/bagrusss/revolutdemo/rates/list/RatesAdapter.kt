package ru.bagrusss.revolutdemo.rates.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.bagrusss.revolutdemo.databinding.ItemRateBinding
import ru.bagrusss.revolutdemo.rates.models.Rate

/**
 * Created by bagrusss on 13.08.2019
 */
class RatesAdapter: RecyclerView.Adapter<RatesVH>() {

    private var rates = emptyList<Rate>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatesVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRateBinding.inflate(inflater, parent, false)
        return RatesVH(binding)
    }

    override fun onBindViewHolder(holder: RatesVH, position: Int) {
        val rate = rates[position]
        holder.onBind(rate)
    }

    override fun getItemCount() = rates.size

    fun swap(newRates: List<Rate>) {
        rates = newRates
        notifyDataSetChanged()
    }

}