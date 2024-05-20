package com.example.realtimelocation.data.di

import android.net.Uri
import com.example.realtimelocation.R
import com.example.realtimelocation.data.location.CarLocationRepository
import com.example.realtimelocation.data.location.GetCarLocation
import com.example.realtimelocation.data.network.CarLocationServiceDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.core.scope.Scope
import org.koin.dsl.module

val carLocationDataModule = module {
    single<Uri> { Uri.parse(androidContext().resources.getString(R.string.server_url)) }
    single { CarLocationServiceDataSource(get()) }
    single { CarLocationRepository(get()) }
    factory<GetCarLocation> { GetCarLocation(carLocationRepository::get) }
}

private val Scope.carLocationRepository: CarLocationRepository get() = get()
