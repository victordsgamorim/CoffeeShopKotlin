package com.victor.coffeeshop_kotlin.ui.main.state

sealed class MainStateEvent {

    object LaunchCoffeeShopListEvent : MainStateEvent()
    object CoffeeShopItemEvent : MainStateEvent()
    object None : MainStateEvent()
}