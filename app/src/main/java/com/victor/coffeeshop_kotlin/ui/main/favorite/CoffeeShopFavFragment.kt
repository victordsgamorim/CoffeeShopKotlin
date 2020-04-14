package com.victor.coffeeshop_kotlin.ui.main.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.victor.coffeeshop_kotlin.R
import com.victor.coffeeshop_kotlin.ui.BaseFragment

class CoffeeShopFavFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_coffee_shop_fav, container, false)
    }
}