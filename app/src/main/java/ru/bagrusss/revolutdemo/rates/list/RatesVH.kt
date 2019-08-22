package ru.bagrusss.revolutdemo.rates.list

import ru.bagrusss.revolutdemo.databinding.ItemRateBinding
import ru.bagrusss.revolutdemo.glide.GlideApp
import ru.bagrusss.revolutdemo.rates.RatesVM
import ru.bagrusss.revolutdemo.rates.models.Rate
import ru.bagrusss.revolutdemo.util.recycler.MvvmViewHolder

/**
 * Created by bagrusss on 13.08.2019
 */
class RatesVH(binding: ItemRateBinding,
              vm: RatesVM): MvvmViewHolder<ItemRateBinding, RatesVM, Rate>(binding, vm) {

    private lateinit var currentData: Rate

    init {
        itemView.setOnClickListener {
            vm.ratesClicked(adapterPosition, currentData)
        }
    }

    override fun onBind(data: Rate) {
        currentData = data
        binding.run {
            rateTitle.text = data.title
            rateDescription.text = data.description
            rateValue.setText(data.cost.toString())
            GlideApp.with(rateCountry)
                    .load(data.imgUrl)
                    .centerCrop()
                    .into(rateCountry)
        }
    }

}