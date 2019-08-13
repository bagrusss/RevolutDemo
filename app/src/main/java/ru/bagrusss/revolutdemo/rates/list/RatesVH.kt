package ru.bagrusss.revolutdemo.rates.list

import ru.bagrusss.revolutdemo.databinding.ItemRateBinding
import ru.bagrusss.revolutdemo.rates.models.Rate
import ru.bagrusss.revolutdemo.util.recycler.BindingViewHolder

/**
 * Created by bagrusss on 13.08.2019
 */
class RatesVH(binding: ItemRateBinding): BindingViewHolder<ItemRateBinding, Rate>(binding) {

    override fun onBind(data: Rate) {
        binding.run {
            rateTitle.text = data.title
            rateDescription.text = data.description
            rateValue.setText(data.cost.toString())
        }
    }

}