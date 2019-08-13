package ru.bagrusss.revolutdemo.mvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.DaggerActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * Created by bagrusss on 12.08.2019
 */
abstract class MvvmActivity<DB : ViewDataBinding, VM : BaseViewModel<*>> : DaggerAppCompatActivity() {

    protected lateinit var binding: DB

    @Inject
    lateinit var vm: VM

    protected abstract val layout: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layout)
        lifecycle.addObserver(vm)
    }

    override fun onDestroy() {
        lifecycle.removeObserver(vm)
        super.onDestroy()
    }


}