package ru.bagrusss.revolutdemo.screens.rates.list

import android.view.View
import ru.bagrusss.revolutdemo.databinding.ItemRateBinding
import ru.bagrusss.revolutdemo.screens.rates.RateEditWatcher
import ru.bagrusss.revolutdemo.screens.rates.RatesVM
import ru.bagrusss.revolutdemo.screens.rates.models.Rate
import ru.bagrusss.revolutdemo.util.keyboard.showKeyboard
import ru.bagrusss.revolutdemo.util.recycler.MvvmViewHolder

/**
 * Created by bagrusss on 13.08.2019
 */
class RateViewHolder(
    binding: ItemRateBinding,
    vm: RatesVM
) : MvvmViewHolder<ItemRateBinding, RatesVM, Rate>(binding, vm), View.OnClickListener {

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
        itemView.setOnClickListener(this)
        binding.preventEdit.setOnClickListener(this)
        binding.data = itemData
        binding.rateValue.addTextChangedListener(currentRateWatcher)
    }

    override fun onBind(data: Rate) = itemData.run {
        title.set(data.title)
        description.set(data.description)
        imgSrc.set(data.imgUrl)
        cost.set(data.cost)
        costActive.set(adapterPosition == 0)
    }

    override fun onClick(v: View?) = rateChanged()

    private fun rateChanged() {
        val costText = binding.rateValue.text.toString()
        val title = itemData.title.get()
        if (adapterPosition > 0 && title != null && costText.isNotEmpty()) {
            vm.currentRateCostChanged(title, costText)
            binding.rateValue.run {
                requestFocus()
                setSelection(text.length)
                showKeyboard()
            }
        }
    }

}