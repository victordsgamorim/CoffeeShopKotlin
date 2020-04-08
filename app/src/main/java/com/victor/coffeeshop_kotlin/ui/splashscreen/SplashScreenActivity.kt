package com.victor.coffeeshop_kotlin.ui.splashscreen

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.victor.coffeeshop_kotlin.R
import com.victor.coffeeshop_kotlin.session.SessionManager
import com.victor.coffeeshop_kotlin.ui.BaseActivity
import com.victor.coffeeshop_kotlin.ui.splashscreen.state.SplashScreenStateEvent.*
import com.victor.coffeeshop_kotlin.viewmodel.ViewModelProviderFactory
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashScreenActivity : BaseActivity() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    lateinit var viewModel: SplashScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        initViewModel()
        loadResultFromCurrentLocation()
        viewModel.dataState.observe(this, Observer { dataState ->
            dataState.data?.let { data ->
                data.data?.let { event ->
                    event.getContentIfNotHandled()?.let { viewState ->
                        Log.i("onCreate", viewState.result.toString())
                    }
                }
            }
        })
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, factory).get(SplashScreenViewModel::class.java)
    }

    private fun loadResultFromCurrentLocation() {
        viewModel.setStateEvent(LaunchCoffeeShopListEvent)
    }
}
