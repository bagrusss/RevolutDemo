package ru.bagrusss.revolutdemo.util.recycler

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by bagrusss on 13.01.2020
 */
abstract class DataAdapter<D, VH : MvvmViewHolder<*, *, D>> : RecyclerView.Adapter<VH>() {

    protected abstract val items: MutableList<D>

    protected abstract fun provideDiffUtilsCallback(newItems: List<D>): DiffUtil.Callback

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: VH, position: Int) = holder.onBind(items[position])

    open fun swap(newItems: List<D>) {
        val callback = provideDiffUtilsCallback(newItems)
        val diffResult = DiffUtil.calculateDiff(callback)
        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

}