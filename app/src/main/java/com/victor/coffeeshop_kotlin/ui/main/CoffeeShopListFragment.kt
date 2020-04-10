package com.victor.coffeeshop_kotlin.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.victor.coffeeshop_kotlin.R
import com.victor.coffeeshop_kotlin.persistence.dao.CoffeeDao
import com.victor.coffeeshop_kotlin.ui.BaseFragment
import com.victor.coffeeshop_kotlin.ui.main.recyclerview.adapter.CoffeeShopListAdapter
import kotlinx.android.synthetic.main.fragment_coffee_shop_list.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CoffeeShopListFragment : BaseFragment() {

    @Inject
    lateinit var coffeeDao: CoffeeDao

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_coffee_shop_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CoffeeShopListAdapter()
        activity_main_recyclerview.adapter = adapter

        GlobalScope.launch(IO) {
            val coffees = coffeeDao.getList()
            withContext(Main) {
                adapter.submitList(coffees)
            }
        }

    }
}