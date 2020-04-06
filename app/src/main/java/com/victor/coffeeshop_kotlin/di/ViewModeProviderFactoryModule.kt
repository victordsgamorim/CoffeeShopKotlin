package com.victor.coffeeshop_kotlin.di

import androidx.lifecycle.ViewModelProvider
import com.victor.coffeeshop_kotlin.viewmodel.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModeProviderFactoryModule {

    @Binds
    abstract fun bindViewModelProviderFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory
}