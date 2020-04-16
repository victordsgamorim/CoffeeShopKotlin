package com.victor.coffeeshop_kotlin.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.victor.coffeeshop_kotlin.R
import com.victor.coffeeshop_kotlin.ui.BaseActivity
import com.victor.coffeeshop_kotlin.viewmodel.ViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseActivity() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    lateinit var viewModel: MainViewModel

    private val controller by lazy {
        findNavController(R.id.nav_host_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewModel()

        controller.addOnDestinationChangedListener { _, destination, _ ->
            title = destination.label

            viewModel.uiComponent.observe(this, Observer {


                it?.let { componenet ->
                    if (componenet.appBar)
                        supportActionBar?.show()
                    else
                        supportActionBar?.hide()

                    if (componenet.bottomNav)
                        activity_main_bottom_navigation.visibility = View.VISIBLE
                    else
                        activity_main_bottom_navigation.visibility = View.INVISIBLE


                }
            })
        }






        activity_main_bottom_navigation.setupWithNavController(controller)


    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
    }

    override fun loadProgressBar(load: Boolean) {
        TODO("Not yet implemented")
    }


}
