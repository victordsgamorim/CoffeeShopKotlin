package com.victor.coffeeshop_kotlin.di.main

import androidx.lifecycle.ViewModel
import com.victor.coffeeshop_kotlin.di.ViewModelKey
import com.victor.coffeeshop_kotlin.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}