package com.victor.coffeeshop_kotlin.di.splashscreen

import android.content.SharedPreferences
import com.victor.coffeeshop_kotlin.network.service.OpenApiService
import com.victor.coffeeshop_kotlin.persistence.dao.AddressDao
import com.victor.coffeeshop_kotlin.persistence.dao.CoffeeDao
import com.victor.coffeeshop_kotlin.persistence.dao.CoffeeShopDao
import com.victor.coffeeshop_kotlin.repository.SplashScreenRepository
import com.victor.coffeeshop_kotlin.session.SessionManager
import dagger.Module
import dagger.Provides

@Module
class SplashScreenModule {

    @SplashScreenScope
    @Provides
    fun provideSplashScreenRepository(
        service: OpenApiService,
        sessionManager: SessionManager,
        coffeShopDao: CoffeeShopDao,
        coffeeDao: CoffeeDao,
        addressDao: AddressDao,
        pref: SharedPreferences,
        prefEditor: SharedPreferences.Editor
    ): SplashScreenRepository {
        return SplashScreenRepository(
            service,
            sessionManager,
            coffeShopDao,
            coffeeDao,
            addressDao,
            pref,
            prefEditor
        )
    }
}