package com.victor.coffeeshop_kotlin.network.service

import androidx.lifecycle.LiveData
import com.victor.coffeeshop_kotlin.model.dto.GooglePlaceDto
import com.victor.coffeeshop_kotlin.util.GenericApiResponse
import com.victor.coffeeshop_kotlin.util.PLACE_API_GET_TEST
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenApiService {

    @GET(PLACE_API_GET_TEST)
    fun getCoffeeListForTest(): Call<GooglePlaceDto>

    @GET("place/nearbysearch/json?")
    fun getPlacesSuggestion(
        @Query("location") location: String,
        @Query("radius") radius: Float,
        @Query("type") type: String,
        @Query("name") name: String,
        @Query("key") key: String
    ): LiveData<GenericApiResponse<GooglePlaceDto>>

}