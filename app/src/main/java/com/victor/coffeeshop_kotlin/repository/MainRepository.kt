package com.victor.coffeeshop_kotlin.repository

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import com.victor.coffeeshop_kotlin.persistence.dao.CoffeeDao
import com.victor.coffeeshop_kotlin.session.SessionManager
import com.victor.coffeeshop_kotlin.ui.DataState
import com.victor.coffeeshop_kotlin.ui.main.list.state.MainViewState
import com.victor.coffeeshop_kotlin.util.AbsentLiveData
import com.victor.coffeeshop_kotlin.util.ApiSuccessResponse
import com.victor.coffeeshop_kotlin.util.GenericApiResponse
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val coffeeDao: CoffeeDao,
    private val sessionManager: SessionManager,
    private val pref: SharedPreferences
) {

    private var repositoryJob: Job? = null

    fun loadCoffeeShopDataBase(): LiveData<DataState<MainViewState>> {
        return loadCoffeeShopFromDatabase()
    }

    private fun loadCoffeeShopFromDatabase(): LiveData<DataState<MainViewState>> {
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

                // busca lista do banco de dados
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