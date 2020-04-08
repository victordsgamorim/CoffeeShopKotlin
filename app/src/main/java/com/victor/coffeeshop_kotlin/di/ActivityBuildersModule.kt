package com.victor.coffeeshop_kotlin.di

import com.victor.coffeeshop_kotlin.di.main.FragmentBuildersModule
import com.victor.coffeeshop_kotlin.di.main.MainModule
import com.victor.coffeeshop_kotlin.di.main.MainScope
import com.victor.coffeeshop_kotlin.di.main.MainViewModelModule
import com.victor.coffeeshop_kotlin.di.splashscreen.SplashScreenModule
import com.victor.coffeeshop_kotlin.di.splashscreen.SplashScreenScope
import com.victor.coffeeshop_kotlin.di.splashscreen.SplashScreenViewModelModule
import com.victor.coffeeshop_kotlin.ui.main.MainActivity
import com.victor.coffeeshop_kotlin.ui.splashscreen.SplashScreenActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @MainScope
    @ContributesAndroidInjector(
        modules = [FragmentBuildersModule::class, MainModule::class, MainViewModelModule::class]
    )
    abstract fun contribuiteMainActivity(): MainActivity

    @SplashScreenScope
    @ContributesAndroidInjector(modules = [SplashScreenModule::class, SplashScreenViewModelModule::class])
    abstract fun contributeSplashScreen(): SplashScreenActivity
}