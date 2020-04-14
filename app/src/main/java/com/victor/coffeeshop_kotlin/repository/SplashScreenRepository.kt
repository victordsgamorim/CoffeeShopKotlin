package com.victor.coffeeshop_kotlin.repository

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
import com.victor.coffeeshop_kotlin.util.AbsentLiveData
import com.victor.coffeeshop_kotlin.util.ApiSuccessResponse
import com.victor.coffeeshop_kotlin.util.CURRENT_LOCATION_SHARED_PREF_KEY
import com.victor.coffeeshop_kotlin.util.GenericApiResponse
import com.victor.coffeeshop_kotlin.util.PlaceParameters.PLACE_CREDENTIAL_KEY
import com.victor.coffeeshop_kotlin.util.PlaceParameters.PLACE_NAME
import com.victor.coffeeshop_kotlin.util.PlaceParameters.PLACE_RADIUS
import com.victor.coffeeshop_kotlin.util.PlaceParameters.PLACE_TYPE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SplashScreenRepository @Inject constructor(
    private val openApiService: OpenApiService,
    private val sessionManager: SessionManager,
    private val coffeeShopDao: CoffeeShopDao,
    private val coffeeDao: CoffeeDao,
    private val addressDao: AddressDao,
    private val pref: SharedPreferences,
    private val prefEditor: SharedPreferences.Editor

) {

    var repositoryJob: Job? = null
    val location = sessionManager.currentLocationString()

    fun attemptToLoadPlacesFromCurrentLocation(): LiveData<DataState<SplashScreenViewState>> {
        val prefLocation = pref.getString(CURRENT_LOCATION_SHARED_PREF_KEY, null)

        /**If current location is equals to location at shared preferences,
         * no need to use internet request for capturing the api data*/
        prefLocation?.let {
            if (prefLocation == location) {
                return loadStoredData()
            }
        }

        return object :
            NetworkBoundResource<GooglePlaceDto, SplashScreenViewState>(
                sessionManager.isNetworkAvailable(),
                true
            ) {
            override suspend fun handleApiSuccessResponse(response: ApiSuccessResponse<GooglePlaceDto>) {

                val api = response.body

                if (api.status != StatusCode.OK.toString()) {
                    return onErrorReturn(api.status, useDialog = true)
                }

                addLocationToSharedPreferences()

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


                return openApiService.getPlacesSuggestion(
                    location, PLACE_RADIUS, PLACE_TYPE, PLACE_NAME, PLACE_CREDENTIAL_KEY
                )
            }

            override fun setJob(job: Job) {
                cancelJob()
                repositoryJob = job
            }

            override suspend fun loadCachedData() {
                //do not do anything.
            }
        }.asLiveData
    }

    private fun loadStoredData(): LiveData<DataState<SplashScreenViewState>> {
        val mutableLiveData = MutableLiveData<DataState<SplashScreenViewState>>()
        mutableLiveData.value = DataState.loading(true)
        mutableLiveData.value =
            DataState.data(data = SplashScreenViewState(sharedPrefStatus = true))
        return mutableLiveData
    }

    private fun addLocationToSharedPreferences() {
        prefEditor.putString(CURRENT_LOCATION_SHARED_PREF_KEY, location)
        prefEditor.apply()
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