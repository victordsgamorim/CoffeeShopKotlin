package com.victor.coffeeshop_kotlin.di.main

import com.victor.coffeeshop_kotlin.network.service.OpenApiService
import com.victor.coffeeshop_kotlin.repository.MainRepository
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @MainScope
    @Provides
    fun provideMainRepository(service: OpenApiService): MainRepository {
        return MainRepository(service)
    }


}