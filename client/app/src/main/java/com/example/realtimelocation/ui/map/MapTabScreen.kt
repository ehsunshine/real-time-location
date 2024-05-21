package com.example.realtimelocation.ui.map

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.realtimelocation.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MarkerComposable
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
            CarMarker(position)
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

@Composable
private fun CarMarker(position: LatLng) {
    MarkerComposable(
        state = MarkerState(
            position = position,
        ),
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .background(Color.Black, RoundedCornerShape(size = 100.dp))
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            // Draw the car icon
            Icon(painter = painterResource(id = R.drawable.car), contentDescription = "Car", tint = Color.White)
        }
    }
}

private const val DEFAULT_ZOOM_LEVEL: Float = 17.0f
private const val DURATION_MS_300: Int = 300