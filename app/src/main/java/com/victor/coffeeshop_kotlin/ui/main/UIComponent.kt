package com.victor.coffeeshop_kotlin.ui.main

class UIComponent(
    val appBar: Boolean = false,
    val bottomNav: Boolean = false
) {
    override fun toString(): String {
        return "UIComponent(appBar=$appBar, bottomNav=$bottomNav)"
    }
}