package com.victor.coffeeshop_kotlin.ui.splashscreen

import androidx.lifecycle.LiveData
import com.victor.coffeeshop_kotlin.model.dto.GooglePlaceDto
import com.victor.coffeeshop_kotlin.repository.SplashScreenRepository
import com.victor.coffeeshop_kotlin.ui.BaseViewModel
import com.victor.coffeeshop_kotlin.ui.DataState
import com.victor.coffeeshop_kotlin.ui.splashscreen.state.SplashScreenStateEvent
import com.victor.coffeeshop_kotlin.ui.splashscreen.state.SplashScreenViewState
import javax.inject.Inject

class SplashScreenViewModel @Inject constructor(private val repository: SplashScreenRepository) :
    BaseViewModel<SplashScreenStateEvent, SplashScreenViewState>() {

    override fun initViewState(): SplashScreenViewState {
        return SplashScreenViewState()
    }

    override fun handleStateEvent(state: SplashScreenStateEvent): LiveData<DataState<SplashScreenViewState>> {
        return when (state) {
            is SplashScreenStateEvent.LaunchCoffeeShopListEvent -> {
                repository.attemptToLoadPlacesFromCurrentLocation()
            }
        }
    }

    fun setGooglePlaceViewState(googlePlaces: GooglePlaceDto) {
        val update = getCurrentViewState()

        if (googlePlaces == update.result) return

        update.result = googlePlaces
        _viewState.value = update
    }

    fun cancelJob() {
        repository.cancelJob()
    }

    override fun onCleared() {
        super.onCleared()
        cancelJob()
    }

}