package com.example.realtimelocation.data.location

import android.location.Location
import android.net.Uri
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.InputStreamReader

internal class CarLocationRepository(
    serverAddress: Uri,
) {
    private val channel = ManagedChannelBuilder
        .forAddress(serverAddress.host, serverAddress.port)
        .usePlaintext()
        .defaultServiceConfig(readJsonFileAsMap())
        .enableRetry()
        .build()

    private fun readJsonFileAsMap(): Map<String, Any> {
        return Gson().fromJson(
            JsonReader(
                InputStreamReader(
                    this::class.java.getResourceAsStream(
                        "retry_config.json"
                    )
                )
            ), Map::class.java
        ) as Map<String, Any>
    }

    fun get(): Flow<Location> = LocationServiceGrpcKt.LocationServiceCoroutineStub(channel)
        .withWaitForReady()
        .getLastKnownLocation(locationRequest { this.carId = "carID" })
        .map {
            Location("gps").apply {
                longitude = it.longitude
                latitude = it.latitude
            }
        }
        .catch { it.printStackTrace() }
}
