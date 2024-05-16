package com.example.realtimelocation.data.location

import android.location.Location
import android.net.Uri
import android.util.Log
import com.example.realtimelocation.R
import com.example.realtimelocation.data.network.CarLocationServiceDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import org.koin.android.ext.koin.androidContext
import org.koin.core.scope.Scope
import org.koin.dsl.module

private class CarLocationRepository(
    private val carLocationServiceDataSource: CarLocationServiceDataSource,
) {

    fun get(): Flow<Location> = flow {
        (1..100).forEach {
            emit(Location("gps").apply {
                latitude = 57.698923 + it * 0.0001
                longitude = 11.977410 + it * 0.0001
            })
            delay(2000)
        }
    }
}

fun interface GetCarLocation {
    operator fun invoke(): Flow<Location>
}

val carLocationDataModule = module {
    single<Uri> { Uri.parse(androidContext().resources.getString(R.string.server_url)) }
    single { CarLocationServiceDataSource(get()) }
    single { CarLocationRepository(get()) }
    factory<GetCarLocation> { GetCarLocation(carLocationRepository::get) }
}

private val Scope.carLocationRepository: CarLocationRepository get() = get()