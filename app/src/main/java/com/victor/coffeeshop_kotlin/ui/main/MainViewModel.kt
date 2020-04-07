package com.victor.coffeeshop_kotlin.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import com.victor.coffeeshop_kotlin.repository.MainRepository
import com.victor.coffeeshop_kotlin.ui.main.state.MainStateEvent
import com.victor.coffeeshop_kotlin.ui.main.state.MainStateEvent.*
import com.victor.coffeeshop_kotlin.ui.main.state.MainViewState
import com.victor.coffeeshop_kotlin.util.AbsentLiveData
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    private val _stateEvent = MutableLiveData<MainStateEvent>()

    private val _viewState = MutableLiveData<MainViewState>()
    val viewState: LiveData<MainViewState>
        get() = _viewState

    val dataState: LiveData<MainViewState> = switchMap(_stateEvent) { stateEvent ->
        when (stateEvent) {
            is LaunchCoffeeShopListEvent -> {
                AbsentLiveData.create<MainViewState>()
            }
            is CoffeeShopItemEvent -> {
                AbsentLiveData.create<MainViewState>()
            }
            is None -> {
                AbsentLiveData.create<MainViewState>()
            }
        }
    }
}