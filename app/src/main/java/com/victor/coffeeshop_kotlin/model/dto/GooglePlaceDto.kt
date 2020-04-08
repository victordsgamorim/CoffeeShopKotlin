package com.victor.coffeeshop_kotlin.model.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.victor.coffeeshop_kotlin.model.domain.CoffeeShop

class GooglePlaceDto(
    @SerializedName("error_message")
    @Expose
    val error_message: String? = null,

    @SerializedName("results")
    @Expose
    val results: List<CoffeeShop>? = null,

    @SerializedName("status")
    @Expose
    val status: String? = null
){
    override fun toString(): String {
        return "GooglePlaceDto(error_message=$error_message, results=$results, status=$status)"
    }
}