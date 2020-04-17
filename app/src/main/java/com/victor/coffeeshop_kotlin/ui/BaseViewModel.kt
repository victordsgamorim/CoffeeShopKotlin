package com.victor.coffeeshop_kotlin.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.Transformations.*
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<StateEvent, ViewState> : ViewModel() {
    private val _stateEvent = MutableLiveData<StateEvent>()

    protected val _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState>
        get() = _viewState

    val dataState: LiveData<DataState<ViewState>> =
        switchMap(_stateEvent) { state ->
            state?.let {
                handleStateEvent(state)
            }
        }


    fun setStateEvent(event: StateEvent) {
        _stateEvent.value = event
    }

    fun getCurrentViewState(): ViewState {
        return _viewState.value?.let { it } ?: initViewState()
    }

    abstract fun initViewState(): ViewState
    abstract fun handleStateEvent(state: StateEvent): LiveData<DataState<ViewState>>


}