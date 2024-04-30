package com.example.realtimelocation.ui.di

import com.example.realtimelocation.ui.activity.MainActivityViewModel
import com.example.realtimelocation.ui.home.HomeTabViewModel
import com.example.realtimelocation.ui.map.MapTabViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val  uiModule = module {

    viewModelOf(::MainActivityViewModel)

    viewModelOf(::HomeTabViewModel)

    viewModelOf(::MapTabViewModel)
}
