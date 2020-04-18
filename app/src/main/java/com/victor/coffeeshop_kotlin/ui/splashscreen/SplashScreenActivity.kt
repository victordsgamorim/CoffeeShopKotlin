package com.victor.coffeeshop_kotlin.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.victor.coffeeshop_kotlin.R
import com.victor.coffeeshop_kotlin.ui.BaseActivity
import com.victor.coffeeshop_kotlin.ui.main.MainActivity
import com.victor.coffeeshop_kotlin.ui.splashscreen.state.SplashScreenStateEvent.LaunchCoffeeShopListEvent
import com.victor.coffeeshop_kotlin.viewmodel.ViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_splash_screen.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.dataState.observe(this, Observer { dataState ->
            onDataStateChange(dataState)

            dataState.data?.let { data ->
                data.data?.let { event ->
                    event.getContentIfNotHandled()?.let { viewState ->

                        if (!viewState.sharedPrefStatus) {
                            val googleplaces =
                                viewState.result?.let { it }
                                    ?: throw IllegalArgumentException("ViewState is empty")

                            viewModel.setGooglePlaceViewState(googleplaces)
                            Log.i("onCreate", googleplaces.toString())
                        }
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

    override fun loadProgressBar(load: Boolean) {
        if (load) {
            activity_splash_screen_progress_bar.visibility = View.VISIBLE
        }

        GlobalScope.launch(IO) {
            delay(2000L)

            withContext(Main) {
                if (!load) {
                    activity_splash_screen_progress_bar.visibility = View.INVISIBLE
                    moveToMaiActivity()
                }
            }
        }

    }

    private fun moveToMaiActivity() {
        Intent(this, MainActivity::class.java).also {
            startActivity(it)
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelJob()
    }
}
