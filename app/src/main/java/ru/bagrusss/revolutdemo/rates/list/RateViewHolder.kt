package ru.bagrusss.revolutdemo.rates.list

import ru.bagrusss.revolutdemo.databinding.ItemRateBinding
import ru.bagrusss.revolutdemo.rates.RateEditWatcher
import ru.bagrusss.revolutdemo.rates.RatesVM
import ru.bagrusss.revolutdemo.rates.models.Rate
import ru.bagrusss.revolutdemo.util.format.formattedMoney
import ru.bagrusss.revolutdemo.util.recycler.MvvmViewHolder

/**
 * Created by bagrusss on 13.08.2019
 */
class RateViewHolder(
    binding: ItemRateBinding,
    vm: RatesVM
) : MvvmViewHolder<ItemRateBinding, RatesVM, Rate>(binding, vm) {

    private val itemData = RateItemData()

    private val currentRateWatcher = RateEditWatcher(
        itemView.context.applicationContext,
        binding.rateValue
    ) { newRateValue ->
        if (adapterPosition == 0) {
            val title = itemData.title.get().orEmpty()
            vm.currentRateCostChanged(title, newRateValue)
        }
    }

    init {
        itemView.setOnClickListener { rateChanged() }
        binding.rateValue.setOnFocusChangeListener { _, active ->
            if (active)
                rateChanged()
        }
        binding.data = itemData
    }

    override fun onBind(data: Rate) = itemData.run {
        title.set(data.title)
        description.set(data.description)
        imgSrc.set(data.imgUrl)
        updateRateValue(data.cost.formattedMoney)
    }

    private fun rateChanged() {
        val costText = itemData.cost.get()
        val title = itemData.title.get()
        if (adapterPosition > 0 && title != null && !costText.isNullOrEmpty()) {
            vm.ratesClicked(adapterPosition, title, costText)
            binding.rateValue.post {
                binding.rateValue.requestFocus()
                binding.rateValue.setSelection(costText.length)
            }
        }
    }

    private fun updateRateValue(newRateValue: String) {
        if (adapterPosition != 0) {
            itemData.cost.set(newRateValue)
        } else {
            binding.rateValue.removeTextChangedListener(currentRateWatcher)
            itemData.cost.set(newRateValue)
            binding.executePendingBindings()
            binding.rateValue.setSelection(newRateValue.length)
            binding.rateValue.addTextChangedListener(currentRateWatcher)
        }
    }

}