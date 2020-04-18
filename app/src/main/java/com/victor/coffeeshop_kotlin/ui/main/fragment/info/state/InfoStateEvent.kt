package com.victor.coffeeshop_kotlin.ui.main.fragment.info.state

sealed class InfoStateEvent {

    data class AddCoffeeIDEvent(val id: String) : InfoStateEvent()
}