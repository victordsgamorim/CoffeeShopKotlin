package com.victor.coffeeshop_kotlin.ui.main.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.victor.coffeeshop_kotlin.ui.BaseFragment

abstract class BaseDataStateFragment : BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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