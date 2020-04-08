package com.victor.coffeeshop_kotlin.di.splashscreen

import com.victor.coffeeshop_kotlin.network.service.OpenApiService
import com.victor.coffeeshop_kotlin.repository.SplashScreenRepository
import com.victor.coffeeshop_kotlin.session.SessionManager
import dagger.Module
import dagger.Provides

@Module
class SplashScreenModule {

    @SplashScreenScope
    @Provides
    fun provideSplashScreenRepository(service: OpenApiService, sessionManager: SessionManager): SplashScreenRepository {
        return SplashScreenRepository(service, sessionManager)
    }
}