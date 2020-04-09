package com.victor.coffeeshop_kotlin.session

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build

class NetworkStatus(private val application: Application) {

    fun checkNetworkStatus(
        onAvailable: (Network?, Boolean) -> Unit,
        onLost: (Network?, Boolean) -> Unit
    ) {
        val cm =
            application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            cm.registerDefaultNetworkCallback(object :
                ConnectivityManager.NetworkCallback() {

                override fun onAvailable(network: Network?) {
                    onAvailable(network, true)
                }

                override fun onLost(network: Network?) {
                    onLost(network, false)
                }
            })
        }

    }
}


