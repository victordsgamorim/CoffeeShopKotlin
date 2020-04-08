package com.victor.coffeeshop_kotlin.ui

import com.victor.coffeeshop_kotlin.session.SessionManager
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity(), OnDataStateChangeListener {

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onDataStateChange(dataState: DataState<*>) {
        loadProgressBar(dataState.load.isLoading)
    }

    abstract fun loadProgressBar(load: Boolean)
}