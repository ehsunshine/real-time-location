package com.example.realtimelocation

import android.app.Application
import com.example.realtimelocation.data.location.carLocationDataModule
import com.example.realtimelocation.data.tabs.bottomTabsDataModule
import com.example.realtimelocation.ui.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RealTimeLocationApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RealTimeLocationApplication)
            modules(
                uiModule,
                bottomTabsDataModule,
                carLocationDataModule,
            )
        }
    }
}