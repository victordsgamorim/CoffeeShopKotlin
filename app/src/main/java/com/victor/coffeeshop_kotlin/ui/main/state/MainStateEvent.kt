package com.victor.coffeeshop_kotlin.ui.main.state

sealed class MainStateEvent {

    object LoadCoffeeShopDatabase : MainStateEvent()
    data class AddCoffeeIDEvent(val id: String) : MainStateEvent()
}