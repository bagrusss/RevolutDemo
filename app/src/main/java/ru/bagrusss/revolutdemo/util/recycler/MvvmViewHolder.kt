package ru.bagrusss.revolutdemo.util.recycler

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import ru.bagrusss.revolutdemo.mvvm.BaseViewModel

/**
 * Created by bagrusss on 13.08.2019
 */
abstract class MvvmViewHolder<B : ViewDataBinding, VM : BaseViewModel<*>, in D>(
    @JvmField protected val binding: B,
    @JvmField protected val vm: VM
) : RecyclerView.ViewHolder(binding.root) {

    abstract fun onBind(data: D)

}