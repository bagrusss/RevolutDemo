package ru.bagrusss.revolutdemo.rates.list

import ru.bagrusss.revolutdemo.databinding.ItemRateBinding
import ru.bagrusss.revolutdemo.rates.RatesVM
import ru.bagrusss.revolutdemo.rates.models.Rate
import ru.bagrusss.revolutdemo.util.recycler.MvvmViewHolder

/**
 * Created by bagrusss on 13.08.2019
 */
class RateViewHolder(binding: ItemRateBinding,
                     vm: RatesVM): MvvmViewHolder<ItemRateBinding, RatesVM, Rate>(binding, vm) {

    private val itemData = RateItemData()

    init {
        itemView.setOnClickListener {
            vm.ratesClicked(adapterPosition, itemData.title.get()!!, itemData.cost.get()!!.toFloat())
        }
        binding.data = itemData
    }

    override fun onBind(data: Rate) {
        itemData.run {
            title.set(data.title)
            description.set(data.description)
            imgSrc.set(data.imgUrl)
            cost.set(data.cost.toString())
        }
    }

}