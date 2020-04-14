package com.victor.coffeeshop_kotlin.di.main

import com.victor.coffeeshop_kotlin.persistence.dao.CoffeeDao
import com.victor.coffeeshop_kotlin.repository.MainRepository
import com.victor.coffeeshop_kotlin.ui.main.recyclerview.adapter.CoffeeShopListAdapter
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @MainScope
    @Provides
    fun provideMainRepository(coffeeDao: CoffeeDao): MainRepository {
        return MainRepository(coffeeDao)
    }

    @MainScope
    @Provides
    fun provideCoffeeShopListAdapter(): CoffeeShopListAdapter {
        return CoffeeShopListAdapter()
    }


}