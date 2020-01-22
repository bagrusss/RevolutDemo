package ru.bagrusss.revolutdemo.screens.rates.list

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.bagrusss.revolutdemo.screens.rates.models.Rate
import ru.bagrusss.revolutdemo.util.recycler.SimpleDataAdapter
import ru.bagrusss.revolutdemo.util.recycler.ViewHolderFactory
import javax.inject.Inject


/**
 * Created by bagrusss on 13.08.2019
 */
class RatesAdapter @Inject constructor(
    private val vhFactory: ViewHolderFactory<RateViewHolder>,
    callback: RatesDiffCallback
) : SimpleDataAdapter<Rate, RateViewHolder>(callback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RateViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return vhFactory.create(inflater, parent)
    }

}