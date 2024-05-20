package com.example.realtimelocation.data.location

import android.location.Location
import com.example.realtimelocation.data.network.CarLocationServiceDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class CarLocationRepository(
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
