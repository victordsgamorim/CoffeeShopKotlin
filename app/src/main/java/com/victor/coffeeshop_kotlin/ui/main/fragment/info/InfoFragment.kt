package com.victor.coffeeshop_kotlin.ui.main.fragment.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.victor.coffeeshop_kotlin.R
import com.victor.coffeeshop_kotlin.ui.main.fragment.BaseInfoFragment
import com.victor.coffeeshop_kotlin.ui.main.UIComponent

class InfoFragment : BaseInfoFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setUIComponent = UIComponent(false, true)

        setViewStateObserver()
    }

    private fun setViewStateObserver() {
        viewModel.viewState.observe(fragmentActivity, Observer { viewState ->
            //update views
        })
    }


}