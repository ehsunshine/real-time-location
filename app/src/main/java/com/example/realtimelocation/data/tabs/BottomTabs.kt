package com.example.realtimelocation.data.tabs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Map
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomTabs(
    val route: String,
    val title: String,
    val icon: ImageVector,
) {
    data object Home : BottomTabs(
        route = "home",
        title = "Home",
        icon = Icons.Rounded.Home,
    )

    data object Map : BottomTabs(
        route = "map",
        title = "Map",
        icon = Icons.Rounded.Map,
    )
}