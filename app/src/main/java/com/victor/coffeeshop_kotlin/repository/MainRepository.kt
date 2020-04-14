package com.victor.coffeeshop_kotlin.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.victor.coffeeshop_kotlin.model.dto.GooglePlaceDto
import com.victor.coffeeshop_kotlin.network.service.OpenApiService
import com.victor.coffeeshop_kotlin.persistence.dao.CoffeeDao
import com.victor.coffeeshop_kotlin.ui.DataState
import com.victor.coffeeshop_kotlin.ui.main.MainViewModel
import com.victor.coffeeshop_kotlin.ui.main.state.MainViewState
import com.victor.coffeeshop_kotlin.ui.splashscreen.state.SplashScreenViewState
import com.victor.coffeeshop_kotlin.util.AbsentLiveData
import com.victor.coffeeshop_kotlin.util.ApiSuccessResponse
import com.victor.coffeeshop_kotlin.util.GenericApiResponse
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepository @Inject constructor(private val coffeeDao: CoffeeDao) {

    private var repositoryJob: Job? = null

    fun loadCoffeeShopDataBase(): LiveData<DataState<MainViewState>> {
        return object : NetworkBoundResource<Void, MainViewState>(
            false,
            false
        ) {
            override suspend fun handleApiSuccessResponse(response: ApiSuccessResponse<Void>) {}

            override fun call(): LiveData<GenericApiResponse<Void>> {
                return AbsentLiveData.create()
            }

            override fun setJob(job: Job) {
                cancelJob()
                repositoryJob = job
            }

            override suspend fun loadCachedData() {

                val list = coffeeDao.getList()

                withContext(Main) {
                    onCompleteReturn(
                        dataState = DataState.data(
                            data = MainViewState(coffeeShop = list)
                        )
                    )
                }

            }

        }.asLiveData
    }

    fun cancelJob() {
        repositoryJob?.cancel()
    }
}