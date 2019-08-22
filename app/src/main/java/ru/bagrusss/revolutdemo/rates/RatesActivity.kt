package ru.bagrusss.revolutdemo.rates

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.google.android.material.snackbar.Snackbar
import ru.bagrusss.revolutdemo.R
import ru.bagrusss.revolutdemo.databinding.ActivityRatesBinding
import ru.bagrusss.revolutdemo.mvvm.MvvmActivity
import ru.bagrusss.revolutdemo.rates.list.RatesAdapter
import javax.inject.Inject

class RatesActivity : MvvmActivity<ActivityRatesBinding, RatesVM>() {

    @Inject lateinit var ratesAdapter: RatesAdapter

    override val layout = R.layout.activity_rates

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.ratesList.run {
            layoutManager = LinearLayoutManager(context)
            adapter = ratesAdapter
            (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
            itemAnimator = object : DefaultItemAnimator() {
                override fun onAnimationFinished(viewHolder: RecyclerView.ViewHolder) {
                    if (viewHolder.oldPosition != 0 && viewHolder.adapterPosition == 0) {
                        vm.ratesAnimationsEnded()
                    }
                }
            }
        }
        setSupportActionBar(binding.toolbar)

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
