package com.victor.coffeeshop_kotlin.network.service

import com.victor.coffeeshop_kotlin.model.dto.GooglePlaceDto
import com.victor.coffeeshop_kotlin.util.PLACE_API_GET
import retrofit2.Call
import retrofit2.http.GET

interface OpenApiService {

    @GET(PLACE_API_GET)
    fun getCoffeeListForTest(): Call<GooglePlaceDto>

}