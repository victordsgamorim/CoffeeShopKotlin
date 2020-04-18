package com.victor.coffeeshop_kotlin.ui.main.fragment.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.victor.coffeeshop_kotlin.BR
import com.victor.coffeeshop_kotlin.databinding.FragmentInfoBinding
import com.victor.coffeeshop_kotlin.ui.main.UIComponent
import com.victor.coffeeshop_kotlin.ui.main.fragment.BaseDataStateFragment

class InfoFragment : BaseDataStateFragment() {
    private lateinit var binding: FragmentInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setUIComponent = UIComponent(true, true)

        setViewStateObserver()
    }

    private fun setViewStateObserver() {
        binding.viewModel = viewModel
    }


}