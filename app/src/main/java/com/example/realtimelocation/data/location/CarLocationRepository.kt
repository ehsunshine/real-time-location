package com.example.realtimelocation.data.location

import android.location.Location
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.scope.Scope
import org.koin.dsl.module

private class CarLocationRepository {
    fun get() = flow {
        (1 .. 100).forEach {
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
    single { CarLocationRepository() }
    factory<GetCarLocation> { GetCarLocation(carLocationRepository::get) }
}

private val Scope.carLocationRepository: CarLocationRepository get() = get()