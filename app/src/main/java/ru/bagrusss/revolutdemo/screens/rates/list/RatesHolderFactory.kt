package ru.bagrusss.revolutdemo.screens.rates.list

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.bagrusss.revolutdemo.databinding.ItemRateBinding
import ru.bagrusss.revolutdemo.screens.rates.RatesVM
import ru.bagrusss.revolutdemo.util.recycler.ViewHolderFactory
import javax.inject.Inject

/**
 * Created by bagrusss on 23.01.2020
 */
class RatesHolderFactory @Inject constructor(
    private val vm: RatesVM
) : ViewHolderFactory<RateViewHolder> {

    override fun create(inflater: LayoutInflater, parent: ViewGroup): RateViewHolder {
        val binding = ItemRateBinding.inflate(inflater, parent, false)
        return RateViewHolder(binding, vm)
    }

}