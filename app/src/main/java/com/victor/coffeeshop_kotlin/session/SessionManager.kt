package com.victor.coffeeshop_kotlin.session

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.android.gms.maps.model.LatLng
import com.victor.coffeeshop_kotlin.util.CoroutineJobInitialization
import com.victor.coffeeshop_kotlin.util.ErrorMessage
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import javax.inject.Inject


class SessionManager @Inject constructor(
    private val connection: NetworkStatus
) : CoroutineJobInitialization {

    private lateinit var coroutineScope: CoroutineScope
    private lateinit var job: CompletableJob

    private var isConnectionAvailable = false

    init {
        initCoroutineJob()
    }

    fun currentLocationString(): String {
        val latlng = loadCurrentLocation()
        return "${latlng.latitude},${latlng.longitude}"
    }

    private fun loadCurrentLocation(): LatLng {

        /**This LatLng is currently only for test*/
        return LatLng(53.3496055, -6.2601481)
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun isNetworkAvailable(): Boolean {

        coroutineScope.launch {
            delay(0)
            launch {
                checkConnectivity()
            }
        }

        runBlocking {
            delay(1000L)
            if (!job.isCompleted) {
                onCancelledJob(ErrorMessage.CANCEL_JOB_TIMEOUT)
            }
        }

        return isConnectionAvailable
    }

    private suspend fun checkConnectivity() {
        connection.checkNetworkStatus(
            onAvailable = { _, connection ->
                onCompletedJob(connection)
            }, onLost = { _, connection ->
                onCompletedJob(connection)
            })
    }


    private fun onCompletedJob(isConnectionAvailable: Boolean) {
        job.complete()
        this.isConnectionAvailable = isConnectionAvailable
    }

    private fun onCancelledJob(message: String?) {
        job.cancel(CancellationException(message))
    }

    override fun initCoroutineJob(): Job {
        job = Job()
        job.invokeOnCompletion { throwable ->
            if (job.isCancelled) {
                throwable?.let {
                    Log.e("NetworkJobCancelled", "${it.message}")
                } ?: Log.e("NetworkJobCancelled", ErrorMessage.CANCEL_JOB_UNKNOWN_ERROR)
            }
        }

        coroutineScope = CoroutineScope(IO + job)
        return job
    }


}

