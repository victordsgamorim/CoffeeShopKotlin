package com.victor.coffeeshop_kotlin.di.main

import com.victor.coffeeshop_kotlin.ui.main.CoffeeShopListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeCoffeeShopList(): CoffeeShopListFragment

}