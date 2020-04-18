package com.victor.coffeeshop_kotlin.ui.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
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
    lateinit var appBarConfiguration: AppBarConfiguration

    private val controller by lazy {
        findNavController(R.id.nav_host_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(main_activity_toolbar)

        appBarConfiguration = AppBarConfiguration(controller.graph)
        setupActionBarWithNavController(controller, appBarConfiguration)

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
                        activity_main_bottom_navigation.visibility = View.GONE


                }
            })
        }

        activity_main_bottom_navigation.setupWithNavController(controller)


    }

    override fun onSupportNavigateUp(): Boolean {
        return controller.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
    }

    override fun loadProgressBar(load: Boolean) {
        TODO("Not yet implemented")
    }


}
