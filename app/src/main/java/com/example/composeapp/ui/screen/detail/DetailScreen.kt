package com.example.composeapp.ui.screen.detail

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

const val CITY_ID = "city_id"

@Composable
fun DetailScreen(navController: NavController, cityId: String?) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        Log.d("Compose", cityId ?: "")
        Text(text = "Detail screen")
    }
}