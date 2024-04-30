package com.example.realtimelocation.ui.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch

@Composable
fun MapTabScreen(
    viewModel: MapTabViewModel,
) {
    val carLocation by viewModel.carLocation.collectAsState()
    val cameraPositionState = rememberCameraPositionState()
    val coroutineScope = rememberCoroutineScope()

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
    ) {
        // Draw the car location on the map
        carLocation?.let { position ->
            Marker(
                state = MarkerState(
                    position = position,
                )
            )
            coroutineScope.launch {
                cameraPositionState.animate(
                    CameraUpdateFactory.newLatLngZoom(
                        position,
                        DEFAULT_ZOOM_LEVEL
                    ), DURATION_MS_300
                )
            }
        }
    }
}

private const val DEFAULT_ZOOM_LEVEL: Float = 17.0f
private const val DURATION_MS_300: Int = 300