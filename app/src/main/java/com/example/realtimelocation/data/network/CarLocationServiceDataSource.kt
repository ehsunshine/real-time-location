package com.example.realtimelocation.data.network

import android.location.Location
import android.net.Uri
import android.util.Log
import com.example.realtimelocation.data.location.LocationServiceGrpcKt
import com.example.realtimelocation.data.location.Service
import com.example.realtimelocation.data.location.locationRequest
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import io.grpc.CallOptions
import io.grpc.Channel
import io.grpc.ClientCall
import io.grpc.ClientInterceptor
import io.grpc.ForwardingClientCall.SimpleForwardingClientCall
import io.grpc.ManagedChannelBuilder
import io.grpc.Metadata
import io.grpc.MethodDescriptor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.io.InputStreamReader
import java.util.concurrent.TimeUnit

class CarLocationServiceDataSource(
    serviceAddress: Uri,
) {

    fun getLastKnownLocation(vin: String): Flow<Location> {
       return flowOf()
    }
}
