package com.victor.coffeeshop_kotlin.ui.main.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import com.victor.coffeeshop_kotlin.model.domain.Coffee
import com.victor.coffeeshop_kotlin.ui.BaseFragment

abstract class BaseInfoFragment : BaseFragment() {

    protected lateinit var coffee: Coffee

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDataStateObserver()
    }

    private fun setDataStateObserver() {
        viewModel.dataState.observe(fragmentActivity, Observer { dataState ->
            dataState.data?.let { data ->
                data.data?.let { event ->
                    event.getContentIfNotHandled()?.let { viewState ->
                        viewState.coffee?.let {
                            viewModel.setCoffeeShop(it)
                        }
                    }
                }
            }
        })
    }
}