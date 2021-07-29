package com.example.composeapp.ui.screen.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composeapp.ui.screen.Screen

@Composable
fun MainScreen(navController: NavController) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        Text(text = "Main screen")
        Button(onClick = {
            navController.navigate(Screen.Detail.putArgs(cityId = "Hmmm"))
        }) {
            Text(text = "Go to detail screen")
        }
    }
}