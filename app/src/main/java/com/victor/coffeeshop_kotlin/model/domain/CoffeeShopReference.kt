package com.victor.coffeeshop_kotlin.model.domain

import android.graphics.Bitmap

class CoffeeShopReference(
    val name: String,
    val openNow: Boolean,
    val photo: Bitmap,
    val priceLevel: Int,
    val rating: Double,
    val address: String
) {
}