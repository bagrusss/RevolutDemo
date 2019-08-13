package ru.bagrusss.revolutdemo.util.recycler

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by bagrusss on 13.08.2019
 */
abstract class BindingViewHolder<B: ViewDataBinding, in D>(@JvmField protected val binding: B): RecyclerView.ViewHolder(binding.root) {

    abstract fun onBind(data: D)

}