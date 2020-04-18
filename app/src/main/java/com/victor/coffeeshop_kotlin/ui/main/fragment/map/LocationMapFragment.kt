package com.victor.coffeeshop_kotlin.ui.main.fragment.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.victor.coffeeshop_kotlin.R
import com.victor.coffeeshop_kotlin.ui.main.fragment.BaseDataStateFragment
import com.victor.coffeeshop_kotlin.ui.main.UIComponent

class LocationMapFragment : BaseDataStateFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setUIComponent = UIComponent(appBar = false, bottomNav = true)
    }
}