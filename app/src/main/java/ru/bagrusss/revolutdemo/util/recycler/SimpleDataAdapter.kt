package ru.bagrusss.revolutdemo.util.recycler

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.bagrusss.revolutdemo.screens.rates.list.RateViewHolder

/**
 * Created by bagrusss on 13.01.2020
 */
abstract class SimpleDataAdapter<D, VH : MvvmViewHolder<*, *, D>>(
    callback: DiffUtil.ItemCallback<D>
) : ListAdapter<D, VH>(callback) {

    override fun onBindViewHolder(holder: VH, position: Int) = holder.onBind(getItem(position))

    override fun onBindViewHolder(
        holder: VH,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            val item = payloads[0] as D
            holder.onBind(item)
        }
    }

}