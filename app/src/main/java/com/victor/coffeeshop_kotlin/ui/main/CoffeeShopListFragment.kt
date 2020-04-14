package com.victor.coffeeshop_kotlin.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.victor.coffeeshop_kotlin.R
import com.victor.coffeeshop_kotlin.ui.BaseFragment
import com.victor.coffeeshop_kotlin.ui.main.recyclerview.adapter.CoffeeShopListAdapter
import com.victor.coffeeshop_kotlin.ui.main.state.MainStateEvent
import kotlinx.android.synthetic.main.fragment_coffee_shop_list.*
import javax.inject.Inject

class CoffeeShopListFragment : BaseFragment() {

    @Inject
    lateinit var adapter: CoffeeShopListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadCoffeesShopDatabase()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_coffee_shop_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModelObservers()
    }

    private fun setViewModelObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            dataState.data?.let { data ->
                data.data?.let { event ->
                    event.getContentIfNotHandled()?.let { viewState ->
                        viewModel.setCoffeeShopList(viewState.coffeeShop)
                    }
                }

            }
        })

        viewModel.viewState.observe(viewLifecycleOwner, Observer { viewState ->
            val coffeeShop = viewState.coffeeShop
                ?: throw NullPointerException("CoffeeShop list ViewState value is Null")

            adapter.submitList(coffeeShop)
            activity_main_recyclerview.adapter = adapter

        })
    }

    private fun loadCoffeesShopDatabase() {
        viewModel.setStateEvent(MainStateEvent.LoadCoffeeShopDatabase)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.cancelJob()
    }
}