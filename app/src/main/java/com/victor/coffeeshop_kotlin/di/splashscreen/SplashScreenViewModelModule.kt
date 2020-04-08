package com.victor.coffeeshop_kotlin.di.splashscreen

import androidx.lifecycle.ViewModel
import com.victor.coffeeshop_kotlin.di.ViewModelKey
import com.victor.coffeeshop_kotlin.ui.splashscreen.SplashScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SplashScreenViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashScreenViewModel::class)
    abstract fun bindSplashScreenViewModel(viewModel: SplashScreenViewModel): ViewModel
}