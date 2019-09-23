package ru.bagrusss.revolutdemo.rates.list

import android.text.Editable
import ru.bagrusss.revolutdemo.databinding.ItemRateBinding
import ru.bagrusss.revolutdemo.rates.RatesVM
import ru.bagrusss.revolutdemo.rates.models.Rate
import ru.bagrusss.revolutdemo.util.recycler.MvvmViewHolder
import ru.bagrusss.revolutdemo.util.text.SimpleTextWatcher
import java.math.BigDecimal
import java.math.RoundingMode

/**
 * Created by bagrusss on 13.08.2019
 */
class RateViewHolder(
    binding: ItemRateBinding,
    vm: RatesVM
) : MvvmViewHolder<ItemRateBinding, RatesVM, Rate>(binding, vm) {

    private val itemData = RateItemData()

    private val summWatcher = object : SimpleTextWatcher() {
        override fun afterTextChanged(s: Editable) {
            if (adapterPosition == 0) {
                val textCost = s.toString()
                val source = if (textCost.isEmpty())
                                 binding.rateValue.hint.toString()
                             else textCost
                val cost = source.toBigDecimal()
                val title = itemData.title.get().orEmpty()
                vm.currentRateCostChanged(title, cost)
            }
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
        binding.rateValue.removeTextChangedListener(summWatcher)
        if (adapterPosition == 0) {
            binding.rateValue.run {
                val textCost = data.cost.toPlainString()
                cost.set(textCost)
                binding.executePendingBindings()
                setSelection(textCost.length)
            }
        } else {
            val textCost = if (data.cost.abs() > BigDecimal.ZERO) {
                data.cost
                    .setScale(2, RoundingMode.HALF_UP)
                    .stripTrailingZeros()
                    .toPlainString()
            } else {
                "0" // http://hg.openjdk.java.net/jdk8/jdk8/jdk/rev/2ee772cda1d6
            }
            cost.set(textCost)
        }
        binding.rateValue.addTextChangedListener(summWatcher)
    }

    private fun rateChanged() {
        val costText = itemData.cost.get()
        val title = itemData.title.get()
        if (adapterPosition > 0 && title != null && !costText.isNullOrEmpty()) {
            vm.ratesClicked(adapterPosition, title, costText.toBigDecimal())
            binding.rateValue.post {
                binding.rateValue.requestFocus()
                binding.rateValue.setSelection(costText.length)
            }
        }
    }

}