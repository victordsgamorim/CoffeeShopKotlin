package com.victor.coffeeshop_kotlin.retrofit

import com.victor.coffeeshop_kotlin.model.CoffeeShop
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WebService {

    private val service = AppRetrofit().coffeeShopService()

    private fun <T> execute(
        call: Call<T>,
        onSuccess: (response: T?) -> Unit,
        onFail: (error: String?) -> Unit
    ) {

        call.enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                onFail(t.message)
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    onSuccess(response.body())
                    
                } else {
                    onFail("Error after trying to get info data!")
                }
            }
        })
    }

    fun searchAll(
        onSuccess: (List<CoffeeShop>?) -> Unit,
        onFail: (error: String?) -> Unit
    ) {
        execute(service.getCoffeeShop(), onSuccess, onFail)
    }
}