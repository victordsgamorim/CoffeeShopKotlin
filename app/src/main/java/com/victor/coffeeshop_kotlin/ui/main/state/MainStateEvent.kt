package com.victor.coffeeshop_kotlin.ui.main.state

sealed class MainStateEvent {

    object LoadCoffeeShopDatabase : MainStateEvent()
}