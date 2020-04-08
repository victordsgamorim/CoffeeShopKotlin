package com.victor.coffeeshop_kotlin.ui.splashscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.Transformations.*
import androidx.lifecycle.ViewModel
import com.victor.coffeeshop_kotlin.repository.SplashScreenRepository
import com.victor.coffeeshop_kotlin.ui.DataState
import com.victor.coffeeshop_kotlin.ui.splashscreen.state.SplashScreenStateEvent
import com.victor.coffeeshop_kotlin.ui.splashscreen.state.SplashScreenStateEvent.*
import com.victor.coffeeshop_kotlin.ui.splashscreen.state.SplashScreenViewState
import com.victor.coffeeshop_kotlin.util.AbsentLiveData
import javax.inject.Inject

class SplashScreenViewModel
@Inject constructor(
    private val repository: SplashScreenRepository
) : ViewModel() {

    private val _stateEvent = MutableLiveData<SplashScreenStateEvent>()

    private val _viewState = MutableLiveData<SplashScreenViewState>()
    val viewState: LiveData<SplashScreenViewState>
        get() = _viewState

    val dataState: LiveData<DataState<SplashScreenViewState>> = switchMap(_stateEvent) { state ->
        state?.let {
            handleStateEvent(state)
        }
    }

    private fun handleStateEvent(state: SplashScreenStateEvent): LiveData<DataState<SplashScreenViewState>> {
        return when (state) {
            is LaunchCoffeeShopListEvent -> {
                repository.attemptToLoadPlacesFromCurrentLocation()
            }
        }
    }

    fun setStateEvent(event: SplashScreenStateEvent) {
        _stateEvent.value = event
    }


}