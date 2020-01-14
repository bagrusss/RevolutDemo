package ru.bagrusss.revolutdemo.rates

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import ru.bagrusss.revolutdemo.R
import ru.bagrusss.revolutdemo.databinding.ActivityRatesBinding
import ru.bagrusss.revolutdemo.mvvm.MvvmActivity
import ru.bagrusss.revolutdemo.rates.list.RatesAdapter
import ru.bagrusss.revolutdemo.rates.list.RatesItemAnimator
import javax.inject.Inject

class RatesActivity : MvvmActivity<ActivityRatesBinding, RatesVM>() {

    @Inject lateinit var ratesAdapter: RatesAdapter

    override val layout = R.layout.activity_rates

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.ratesList.run {
            layoutManager = LinearLayoutManager(context)
            adapter = ratesAdapter
            itemAnimator = RatesItemAnimator(vm)
        }
        setSupportActionBar(binding.toolbar)

        binding.data = vm

        vm.ratesChanges.observe(this, Observer(ratesAdapter::swap))
        vm.ratesChanged.observe(this, Observer {
            binding.ratesList.scrollToPosition(0)
            ratesAdapter.moveItem(it)
        })
        vm.errorEvent.observe(this, Observer {
            Snackbar.make(binding.root, R.string.error_text, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.repeat) { vm.ratesChanges() }
                    .show()
        })
    }

}
