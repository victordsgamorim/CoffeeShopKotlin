package com.victor.coffeeshop_kotlin.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.victor.coffeeshop_kotlin.NavGraphDirections
import com.victor.coffeeshop_kotlin.R
import com.victor.coffeeshop_kotlin.ui.main.MainViewModel
import com.victor.coffeeshop_kotlin.viewmodel.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProviderFactory
    lateinit var viewModel: MainViewModel

    protected val fragmentActivity by lazy {
        activity?.run { this } ?: throw IllegalArgumentException("Invalid Activity")
    }

    private val controller by lazy {
        findNavController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(fragmentActivity, factory).get(MainViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_fav, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_favorite_item -> {
                val direction =
                    NavGraphDirections.actionGlobalCoffeeShopFavFragment()
                controller.navigate(direction)
            }
        }
        return super.onOptionsItemSelected(item)
    }


}