package com.icdominguez.scribbledash.di

import com.icdominguez.scribbledash.domain.GetRandomAssetUseCase
import com.icdominguez.scribbledash.ui.screens.drawing.DrawingViewModel
import com.icdominguez.scribbledash.ui.screens.home.HomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { HomeScreenViewModel() }
    viewModel { DrawingViewModel(get()) }
    single { GetRandomAssetUseCase(get()) }
}