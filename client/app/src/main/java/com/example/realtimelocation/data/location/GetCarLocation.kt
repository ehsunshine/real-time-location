package com.example.realtimelocation.data.location

import android.location.Location
import kotlinx.coroutines.flow.Flow

fun interface GetCarLocation {
    operator fun invoke(): Flow<Location>
}
