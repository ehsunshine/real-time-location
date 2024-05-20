package com.example.realtimelocation.data.location

import android.location.Location
import android.net.Uri
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal class CarLocationRepository(
    private val serverAddress: Uri,
) {

    fun get(): Flow<Location> = flowOf()

}
