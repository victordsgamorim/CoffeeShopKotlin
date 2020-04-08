package com.victor.coffeeshop_kotlin.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

abstract class BaseViewModel<StateEvent, ViewState> {
    private val _stateEvent = MutableLiveData<StateEvent>()

    private val _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState>
        get() = _viewState




}