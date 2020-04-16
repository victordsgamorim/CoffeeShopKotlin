package com.victor.coffeeshop_kotlin.di.main

import com.victor.coffeeshop_kotlin.ui.main.favorite.FavoriteFragment
import com.victor.coffeeshop_kotlin.ui.main.info.InfoFragment
import com.victor.coffeeshop_kotlin.ui.main.list.CoffeeShopListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeCoffeeShopListFragment(): CoffeeShopListFragment

    @ContributesAndroidInjector
    abstract fun contributeFavoriteFragment(): FavoriteFragment

    @ContributesAndroidInjector
    abstract fun contributeInforFragment(): InfoFragment

}