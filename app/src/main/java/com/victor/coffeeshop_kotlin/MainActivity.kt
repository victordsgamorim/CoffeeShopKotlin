package com.victor.coffeeshop_kotlin

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.victor.coffeeshop_kotlin.model.CoffeeShop
import com.victor.coffeeshop_kotlin.retrofit.AppRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val call = AppRetrofit().coffeeShopService().getCoffeeShop()

        call.enqueue(object : Callback<List<CoffeeShop>?> {
            override fun onFailure(call: Call<List<CoffeeShop>?>, t: Throwable) {
                Log.e("Error: ", "Error ao tentar buscar coffee shop")
            }

            override fun onResponse(
                call: Call<List<CoffeeShop>?>,
                response: Response<List<CoffeeShop>?>
            ) {
                if (response.isSuccessful) {
                    val coffee = response.body()

                }
            }
        })


    }
}
