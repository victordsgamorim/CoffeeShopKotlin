package com.victor.coffeeshop_kotlin.di.main

import com.victor.coffeeshop_kotlin.ui.main.fragment.favorite.FavoriteFragment
import com.victor.coffeeshop_kotlin.ui.main.fragment.info.InfoFragment
import com.victor.coffeeshop_kotlin.ui.main.fragment.list.CoffeeShopListFragment
import com.victor.coffeeshop_kotlin.ui.main.fragment.map.LocationMapFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeCoffeeShopListFragment(): CoffeeShopListFragment

    @ContributesAndroidInjector
    abstract fun contributeFavoriteFragment(): FavoriteFragment

    @ContributesAndroidInjector
    abstract fun contributeInfoFragment(): InfoFragment

    @ContributesAndroidInjector
    abstract fun contribuiteLocationMapFragment(): LocationMapFragment

}