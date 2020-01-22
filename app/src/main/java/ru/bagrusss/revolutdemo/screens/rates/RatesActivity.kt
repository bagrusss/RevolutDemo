package ru.bagrusss.revolutdemo.screens.rates

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import ru.bagrusss.revolutdemo.R
import ru.bagrusss.revolutdemo.databinding.ActivityRatesBinding
import ru.bagrusss.revolutdemo.mvvm.MvvmActivity
import ru.bagrusss.revolutdemo.screens.rates.list.RatesAdapter
import ru.bagrusss.revolutdemo.screens.rates.list.RatesItemAnimator
import javax.inject.Inject

class RatesActivity : MvvmActivity<ActivityRatesBinding, RatesVM>() {

    @Inject lateinit var ratesAdapter: RatesAdapter
    @Inject lateinit var ratesItemAnimator: RatesItemAnimator

    private val errorSnackBar by lazy {
        Snackbar.make(binding.root, R.string.update_error, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.ok) { }
    }

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

        vm.ratesChanges.observe(this, Observer {
            ratesAdapter.submitList(it, vm::listUpdated)
        })
        vm.mainRateChanged.observe(this, Observer {
            binding.ratesList.smoothScrollToPosition(0)
        })
        vm.errors.observe(this, Observer {
            if (it)
                errorSnackBar.show()
            else errorSnackBar.dismiss()
        })
    }

}
