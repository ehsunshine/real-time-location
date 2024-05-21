package com.example.realtimelocation.ui.map

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realtimelocation.data.location.GetCarLocation
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MapTabViewModel(
    getCarLocation: GetCarLocation
) : ViewModel() {
    private val mutableCarLocation = MutableStateFlow<LatLng?>(null)
    val carLocation: StateFlow<LatLng?> = mutableCarLocation

    init {
        getCarLocation()
            .onEach { mutableCarLocation.value = it.toLatLang() }
            .launchIn(viewModelScope)
    }

    private fun Location.toLatLang() = LatLng(latitude, longitude)
}
