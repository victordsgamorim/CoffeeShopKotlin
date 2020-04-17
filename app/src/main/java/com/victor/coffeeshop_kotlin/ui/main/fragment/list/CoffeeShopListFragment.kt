package com.victor.coffeeshop_kotlin.ui.main.fragment.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.victor.coffeeshop_kotlin.R
import com.victor.coffeeshop_kotlin.ui.BaseFragment
import com.victor.coffeeshop_kotlin.ui.main.UIComponent
import com.victor.coffeeshop_kotlin.ui.main.fragment.list.recyclerview.adapter.CoffeeShopListAdapter
import com.victor.coffeeshop_kotlin.ui.main.state.MainStateEvent.AddIdToSharedPreference
import com.victor.coffeeshop_kotlin.ui.main.state.MainStateEvent.LoadCoffeeShopDatabase
import kotlinx.android.synthetic.main.fragment_coffee_shop_list.*
import javax.inject.Inject

class CoffeeShopListFragment : BaseFragment() {

    @Inject
    lateinit var adapter: CoffeeShopListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadCoffeesShopDatabase()
        setHasOptionsMenu(true)


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
        activity_main_recyclerview.adapter = adapter
        viewModel.setUIComponent = UIComponent(appBar = true, bottomNav = false)
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

        })

        adapter.onItemClick = { coffee ->
            viewModel.setStateEvent(AddIdToSharedPreference(coffee.id))
            val direction =
                CoffeeShopListFragmentDirections.actionCoffeeShopListFragmentToInfoFragment()
            findNavController().navigate(direction)
        }
    }

    private fun loadCoffeesShopDatabase() {
        viewModel.setStateEvent(LoadCoffeeShopDatabase)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.cancelJob()
    }

}