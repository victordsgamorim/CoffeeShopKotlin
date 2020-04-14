package com.victor.coffeeshop_kotlin.ui.main.list.state

sealed class MainStateEvent {

    object LoadCoffeeShopDatabase : MainStateEvent()
}