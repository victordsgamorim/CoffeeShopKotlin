package com.victor.coffeeshop_kotlin.repository

import androidx.lifecycle.LiveData
import com.victor.coffeeshop_kotlin.model.dto.GooglePlaceDto
import com.victor.coffeeshop_kotlin.network.service.OpenApiService
import com.victor.coffeeshop_kotlin.session.SessionManager
import com.victor.coffeeshop_kotlin.ui.DataState
import com.victor.coffeeshop_kotlin.ui.splashscreen.state.SplashScreenViewState
import com.victor.coffeeshop_kotlin.util.ApiSuccessResponse
import com.victor.coffeeshop_kotlin.util.GenericApiResponse
import kotlinx.coroutines.Job
import javax.inject.Inject

class SplashScreenRepository @Inject constructor(
    private val openApiService: OpenApiService,
    private val sessionManager: SessionManager
) {

    var repositoryJob: Job? = null

    fun attemptToLoadPlacesFromCurrentLocation(): LiveData<DataState<SplashScreenViewState>> {

        return object :
            NetworkBoundResource<GooglePlaceDto, SplashScreenViewState>(sessionManager.isNetworkAvailable()) {
            override suspend fun handleApiSuccessResponse(response: ApiSuccessResponse<GooglePlaceDto>) {
                onCompleteReturn(dataState = DataState.data(data = SplashScreenViewState(result = response.body)))

                //add to database

            }

            override fun call(): LiveData<GenericApiResponse<GooglePlaceDto>> {
                val location = sessionManager.currentLocationString()

                return openApiService.getPlacesSuggestion(
                    location,
                    50f,
                    "cafe",
                    "coffeeshop",
                    " AIzaSyDyJqQzhSEuEiPk3A1jWBTJ4m9aaExb_k8 "
                )
            }

            override fun setJob(job: Job) {
                cancelJob()
                repositoryJob = job
            }


        }.asLiveData
    }

    fun cancelJob() {
        repositoryJob?.cancel()
    }

}