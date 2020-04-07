package com.victor.coffeeshop_kotlin.ui.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.victor.coffeeshop_kotlin.R
import com.victor.coffeeshop_kotlin.ui.BaseActivity
import com.victor.coffeeshop_kotlin.viewmodel.ViewModelProviderFactory
import javax.inject.Inject


class MainActivity : BaseActivity() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

    }

}
