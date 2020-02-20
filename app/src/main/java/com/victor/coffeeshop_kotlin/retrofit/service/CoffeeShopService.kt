package com.victor.coffeeshop_kotlin.retrofit.service

import com.victor.coffeeshop_kotlin.model.CoffeeShop
import retrofit2.Call
import retrofit2.http.GET

interface CoffeeShopService {

    @GET("bins/pq0pq")
    fun getCoffeeShop(): Call<List<CoffeeShop>?>
}