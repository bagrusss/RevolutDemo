package ru.bagrusss.revolutdemo.util.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by bagrusss on 23.01.2020
 */
interface ViewHolderFactory<out VH : RecyclerView.ViewHolder> {

    fun create(inflater: LayoutInflater, parent: ViewGroup): VH

}