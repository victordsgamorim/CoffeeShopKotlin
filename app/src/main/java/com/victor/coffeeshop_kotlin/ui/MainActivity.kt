package com.victor.coffeeshop_kotlin.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.victor.coffeeshop_kotlin.R
import com.victor.coffeeshop_kotlin.retrofit.WebService
import com.victor.coffeeshop_kotlin.ui.adapter.CoffeeShopMainScreenAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val service = WebService()
        val adapter = CoffeeShopMainScreenAdapter(this)

        service.searchAll(onSuccess = { shops ->

            shops?.let { adapter.update(it) }

        }, onFail = { error ->
            Log.e(TAG, error?.let { it })
        })

        activity_main_recyclerview.adapter = adapter

    }
}
