package com.example.realtimelocation

import com.example.realtimelocation.data.location.LocationServiceGrpcKt
import com.example.realtimelocation.data.location.Service
import com.example.realtimelocation.data.location.Service.LastKnownLocation
import com.example.realtimelocation.data.location.lastKnownLocation
import io.grpc.Server
import io.grpc.ServerBuilder
import io.grpc.Status
import io.grpc.StatusException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import mockedLocations

class LastKnownLocationServer(private val port: Int) {
    companion object {
        var numberOfServiceCalls = 0
        const val SERVICE_UNAVAILABLE = false
    }
    private val server: Server =
        ServerBuilder
            .forPort(port)
            .addService(LastKnownLocationService())
            .build()

    fun start() {
        server.start()
        println("Server started, listening on $port")
        Runtime.getRuntime().addShutdownHook(
            Thread {
                println("*** shutting down gRPC server since JVM is shutting down")
                this@LastKnownLocationServer.stop()
                println("*** server shut down")
            },
        )
    }

    private fun stop() {
        server.shutdown()
    }

    fun blockUntilShutdown() {
        server.awaitTermination()
    }

    internal class LastKnownLocationService : LocationServiceGrpcKt.LocationServiceCoroutineImplBase() {

        override fun getLastKnownLocation(request: Service.LocationRequest): Flow<Service.LastKnownLocation> = flow {
            if (SERVICE_UNAVAILABLE && numberOfServiceCalls++ < 3) {
                throw StatusException(Status.UNAVAILABLE.withDescription("Service is not ready"))
            }
            numberOfServiceCalls = 0
            if ((request.isInitialized && request.carId.isNotEmpty())) {
                emitLastKnownLocations()
            } else {
                throw StatusException(Status.INVALID_ARGUMENT.withDescription("VIN is required"))
            }
        }

        private suspend fun FlowCollector<LastKnownLocation>.emitLastKnownLocations() {
            mockedLocations.forEach { (long, lat) ->
                emit(lastKnownLocation {
                    latitude = lat
                    longitude = long
                })
                delay(500)
            }
        }
    }
}

fun main() {
    val port = System.getenv("PORT")?.toInt() ?: 50051
    val server = LastKnownLocationServer(port)
    server.start()
    server.blockUntilShutdown()
}

