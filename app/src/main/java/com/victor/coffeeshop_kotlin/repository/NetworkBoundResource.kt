package com.victor.coffeeshop_kotlin.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.victor.coffeeshop_kotlin.ui.DataState
import com.victor.coffeeshop_kotlin.ui.Response
import com.victor.coffeeshop_kotlin.ui.ResponseType
import com.victor.coffeeshop_kotlin.util.ApiEmptyResponse
import com.victor.coffeeshop_kotlin.util.ApiErrorResponse
import com.victor.coffeeshop_kotlin.util.ApiSuccessResponse
import com.victor.coffeeshop_kotlin.util.GenericApiResponse
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

abstract class NetworkBoundResource<Response, ViewState>
    (private val isNetworkAvailable: Boolean) {

    private val result = MediatorLiveData<DataState<ViewState>>()
    private lateinit var coroutineScope: CoroutineScope
    private lateinit var job: CompletableJob

    val asLiveData = result as LiveData<DataState<ViewState>>

    init {

        setJob(initJob())
        setValue(DataState.loading(true))

        if (isNetworkAvailable) {

            coroutineScope.launch {
                delay(0)

                withContext(Main) {
                    val call = call()
                    result.addSource(call) { response ->
                        result.removeSource(call)

                        coroutineScope.launch {
                            handleApiResponse(response)
                        }
                    }
                }
            }
        } else {
            onErrorReturn("Network is not available", useToast = true)
        }
    }

    private suspend fun handleApiResponse(response: GenericApiResponse<Response>) {
        when (response) {
            is ApiSuccessResponse -> {
                handleApiSuccessResponse(response)
            }

            is ApiEmptyResponse -> {
                handleErrorReponse("http 204 empty content")
            }

            is ApiErrorResponse -> {
                handleErrorReponse(response.errorMessage)
            }

        }
    }

    private fun handleErrorReponse(message: String) {
        onErrorReturn(message, useToast = true)
    }

    fun onErrorReturn(
        message: String?,
        useDialog: Boolean = false,
        useToast: Boolean = false
    ) {
        var msg = message
        var typeResponse: ResponseType = ResponseType.None()

        if (msg == null) {
            msg = "Null throwable, Unknown error"
        }

        if (useDialog) {
            typeResponse = ResponseType.Dialog()
        }

        if (useToast) {
            typeResponse = ResponseType.Toast()
        }

        onCompleteReturn(
            DataState.error(
                response = Response(
                    message = msg,
                    responseType = typeResponse
                )
            )
        )

    }

    fun onCompleteReturn(dataState: DataState<ViewState>) {

        GlobalScope.launch(Main) {
            job.complete()
            setValue(dataState)
        }

    }

    private fun setValue(dataState: DataState<ViewState>) {
        result.value = dataState
    }

    private fun initJob(): Job {
        job = Job()
        job.invokeOnCompletion { throwable ->
            if (job.isCancelled) {
                throwable?.let {
                    onErrorReturn(it.message, useToast = true)
                } ?: onErrorReturn(throwable!!.message, useToast = true)
            }
        }

        coroutineScope = CoroutineScope(IO + job)
        return job
    }


    abstract suspend fun handleApiSuccessResponse(response: ApiSuccessResponse<Response>)
    abstract fun call(): LiveData<GenericApiResponse<Response>>
    abstract fun setJob(job: Job)

}