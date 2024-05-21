package com.example.realtimelocation.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.MyLocation
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.realtimelocation.R

@Composable
fun HomeTabScreen(
    homeTabViewModel: HomeTabViewModel
) {
    Column(
        Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        UserHeader()
        CarHeader()
        CarImage()
        CarLocation()
    }
}

@Composable
private fun UserHeader() {
    Row(
        Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = "Welcome Ehsan",
            style = MaterialTheme.typography.headlineMedium
        )
        Icon(
            modifier = Modifier.padding(16.dp),
            imageVector = Icons.Rounded.AccountCircle,
            contentDescription = "User Icon"
        )
    }
}

@Composable
private fun CarHeader() {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = 32.dp),
        horizontalAlignment = CenterHorizontally
    ) {
        Text(
            text = "409km  89%",
            style = MaterialTheme.typography.headlineLarge
        )
    }

}

@Composable
private fun CarImage() {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = 32.dp),
        horizontalAlignment = CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.car_front), contentDescription = "Car Image")
    }
}

@Composable
private fun CarLocation() {
    Card(
        Modifier
            .padding(horizontal = 16.dp, vertical = 32.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Row(
            Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(32.dp),
                imageVector = Icons.Rounded.MyLocation,
                contentDescription = "Location Icon"
            )
            Column(
                Modifier.padding(horizontal = 16.dp),
            ) {
                Text(
                    text = "Bella Center",
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(text = "Your location", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
