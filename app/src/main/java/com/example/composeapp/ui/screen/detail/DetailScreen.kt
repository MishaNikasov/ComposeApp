package com.example.composeapp.ui.screen.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import timber.log.Timber

const val CITY_ID = "city_id"

@Composable
fun DetailScreen(navController: NavController, cityId: String?) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        Timber.d(cityId ?: "")
        Text(text = "Detail screen")
    }
}