package ru.bagrusss.revolutdemo.screens.rates

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import ru.bagrusss.revolutdemo.R
import ru.bagrusss.revolutdemo.databinding.ActivityRatesBinding
import ru.bagrusss.revolutdemo.mvvm.MvvmActivity
import ru.bagrusss.revolutdemo.screens.rates.list.RatesAdapter
import ru.bagrusss.revolutdemo.screens.rates.list.RatesItemAnimator
import javax.inject.Inject

class RatesActivity : MvvmActivity<ActivityRatesBinding, RatesVM>() {

    @Inject lateinit var ratesAdapter: RatesAdapter
    @Inject lateinit var ratesItemAnimator: RatesItemAnimator

    override val layout = R.layout.activity_rates

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.ratesList.run {
            layoutManager = LinearLayoutManager(context)
            adapter = ratesAdapter
            itemAnimator = ratesItemAnimator
        }
        setSupportActionBar(binding.toolbar)

        binding.data = vm

        vm.ratesChanges.observe(this, Observer(ratesAdapter::swap))
        vm.ratesChanged.observe(this, Observer {
            binding.ratesList.scrollToPosition(0)
            ratesAdapter.moveItem(it)
        })

        binding.repeatButton.setOnClickListener {
            it.isEnabled = false
            vm.ratesChanges()
            it.isEnabled = true
        }
    }

}
