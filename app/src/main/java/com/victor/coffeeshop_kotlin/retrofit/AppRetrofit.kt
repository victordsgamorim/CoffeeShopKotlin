package com.victor.coffeeshop_kotlin.retrofit

import com.victor.coffeeshop_kotlin.retrofit.service.CoffeeShopService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppRetrofit {

    fun coffeeShopService() = appRetrofit.create(CoffeeShopService::class.java)

    private val client by lazy {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    private val appRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://api.myjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}