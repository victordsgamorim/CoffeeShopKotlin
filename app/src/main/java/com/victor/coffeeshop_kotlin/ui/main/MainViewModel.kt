package com.victor.coffeeshop_kotlin.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.victor.coffeeshop_kotlin.model.domain.Coffee
import com.victor.coffeeshop_kotlin.repository.MainRepository
import com.victor.coffeeshop_kotlin.ui.BaseViewModel
import com.victor.coffeeshop_kotlin.ui.DataState
import com.victor.coffeeshop_kotlin.ui.main.list.state.MainStateEvent
import com.victor.coffeeshop_kotlin.ui.main.list.state.MainStateEvent.LoadCoffeeShopDatabase
import com.victor.coffeeshop_kotlin.ui.main.list.state.MainViewState
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: MainRepository
) : BaseViewModel<MainStateEvent, MainViewState>() {

    private val _uiComponent = MutableLiveData<UIComponent>()
        .also { it.value = setUIComponent }

    val uiComponent: LiveData<UIComponent>
        get() = _uiComponent

    var setUIComponent = UIComponent()
        set(value) {
            field = value
            _uiComponent.value = value
        }

    override fun handleStateEvent(state: MainStateEvent): LiveData<DataState<MainViewState>> {
        return when (state) {
            LoadCoffeeShopDatabase -> {
                repository.loadCoffeeShopDataBase()
            }
        }
    }

    override fun initViewState(): MainViewState {
        return MainViewState()
    }


    fun setCoffeeShopList(coffeeShop: List<Coffee>?) {
        val update = getCurrentViewState()

        if (update.coffeeShop == coffeeShop) return

        update.coffeeShop = coffeeShop
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