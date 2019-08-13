package ru.bagrusss.revolutdemo.rates

import android.os.Bundle
import ru.bagrusss.revolutdemo.R
import ru.bagrusss.revolutdemo.databinding.ActivityRatesBinding
import ru.bagrusss.revolutdemo.mvvm.MvvmActivity

class RatesActivity : MvvmActivity<ActivityRatesBinding, RatesVM>() {

    override val layout = R.layout.activity_rates

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}
