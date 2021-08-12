package com.example.composeapp.ui.test.nav

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun TestNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            Column (modifier = Modifier.fillMaxWidth()) {
                Text(text = "Home screen")
                Button(onClick = {
                    navController.navigate("list")
                }) {
                    Text(text = "Go to list screen")
                }
            }
        }
        composable("list") {
            ListScreen(navController = navController)
        }
    }
}

@Composable
fun Item() {
    Column {

    }
}