package ru.bagrusss.revolutdemo.rates

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import ru.bagrusss.revolutdemo.R
import ru.bagrusss.revolutdemo.databinding.ActivityRatesBinding
import ru.bagrusss.revolutdemo.mvvm.MvvmActivity
import ru.bagrusss.revolutdemo.rates.list.RatesAdapter

class RatesActivity : MvvmActivity<ActivityRatesBinding, RatesVM>() {

    private val ratesAdapter by lazy(::RatesAdapter)

    override val layout = R.layout.activity_rates

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.ratesList.run {
            adapter = ratesAdapter
            layoutManager = LinearLayoutManager(context)
        }
        vm.ratesChanges.observe(this, Observer(ratesAdapter::swap))
    }

}
