package com.victor.coffeeshop_kotlin.di.main

import android.content.SharedPreferences
import com.victor.coffeeshop_kotlin.persistence.dao.CoffeeDao
import com.victor.coffeeshop_kotlin.repository.MainRepository
import com.victor.coffeeshop_kotlin.session.SessionManager
import com.victor.coffeeshop_kotlin.ui.main.fragment.list.recyclerview.adapter.CoffeeShopListAdapter
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @MainScope
    @Provides
    fun provideMainRepository(
        coffeeDao: CoffeeDao,
        sessionManager: SessionManager,
        pref: SharedPreferences,
        prefEditor: SharedPreferences.Editor
    ): MainRepository {
        return MainRepository(coffeeDao, sessionManager, pref, prefEditor)
    }

    @MainScope
    @Provides
    fun provideCoffeeShopListAdapter(): CoffeeShopListAdapter {
        return CoffeeShopListAdapter()
    }


}