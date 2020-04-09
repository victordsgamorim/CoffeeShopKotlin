package com.victor.coffeeshop_kotlin.repository

import androidx.lifecycle.LiveData
import com.victor.coffeeshop_kotlin.model.domain.Address
import com.victor.coffeeshop_kotlin.model.domain.Coffee
import com.victor.coffeeshop_kotlin.model.domain.CoffeeShop
import com.victor.coffeeshop_kotlin.model.dto.GooglePlaceDto
import com.victor.coffeeshop_kotlin.model.dto.StatusCode
import com.victor.coffeeshop_kotlin.network.service.OpenApiService
import com.victor.coffeeshop_kotlin.persistence.dao.AddressDao
import com.victor.coffeeshop_kotlin.persistence.dao.CoffeeDao
import com.victor.coffeeshop_kotlin.persistence.dao.CoffeeShopDao
import com.victor.coffeeshop_kotlin.session.SessionManager
import com.victor.coffeeshop_kotlin.ui.DataState
import com.victor.coffeeshop_kotlin.ui.splashscreen.state.SplashScreenViewState
import com.victor.coffeeshop_kotlin.util.ApiSuccessResponse
import com.victor.coffeeshop_kotlin.util.GenericApiResponse
import com.victor.coffeeshop_kotlin.util.PlaceParameters.PLACE_CREDENTIAL_KEY
import com.victor.coffeeshop_kotlin.util.PlaceParameters.PLACE_NAME
import com.victor.coffeeshop_kotlin.util.PlaceParameters.PLACE_RADIUS
import com.victor.coffeeshop_kotlin.util.PlaceParameters.PLACE_TYPE
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import java.lang.IllegalArgumentException
import javax.inject.Inject

class SplashScreenRepository @Inject constructor(
    private val openApiService: OpenApiService,
    private val sessionManager: SessionManager,
    private val coffeeShopDao: CoffeeShopDao,
    private val coffeeDao: CoffeeDao,
    private val addressDao: AddressDao
) {

    var repositoryJob: Job? = null

    fun attemptToLoadPlacesFromCurrentLocation(): LiveData<DataState<SplashScreenViewState>> {
        return object :
            NetworkBoundResource<GooglePlaceDto, SplashScreenViewState>(sessionManager.isNetworkAvailable()) {
            override suspend fun handleApiSuccessResponse(response: ApiSuccessResponse<GooglePlaceDto>) {

                val api = response.body

                if (api.status != StatusCode.OK.toString()) {
                    return onErrorReturn(api.status, useDialog = true)
                }

                CoroutineScope(IO).launch {
                    val results = api.results?.let { it }
                        ?: throw IllegalArgumentException("Coffee Shop Suggestions is Null")
                    withContext(Default) {
                        addResponseResultToDataBase(results)
                    }
                }

                onCompleteReturn(dataState = DataState.data(data = SplashScreenViewState(result = api)))
            }

            override fun call(): LiveData<GenericApiResponse<GooglePlaceDto>> {
                val location = sessionManager.currentLocationString()

                return openApiService.getPlacesSuggestion(
                    location, PLACE_RADIUS, PLACE_TYPE, PLACE_NAME, PLACE_CREDENTIAL_KEY
                )
            }

            override fun setJob(job: Job) {
                cancelJob()
                repositoryJob = job
            }


        }.asLiveData
    }

    private suspend fun addResponseResultToDataBase(results: List<CoffeeShop>) {
        for (cs in results) {
            val coffee = Coffee(
                id = cs.id,
                placeId = cs.place_id,
                name = cs.name,
                openNow = cs.opening_hours,
                photo = cs.photos,
                priceLevel = cs.price_level,
                rating = cs.rating
            )

            coffeeDao.insertShop(coffee)

            val address = Address(
                location = cs.geometry,
                address = cs.address,
                coffeeId = cs.id
            )

            addressDao.insertAddress(address)
        }

        coffeeShopDao.addCoffeeShopSuggestions(results)
    }

    fun cancelJob() {
        repositoryJob?.cancel()
    }

}