package com.example.composeapp.ui.test.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController

@Composable
fun TestNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "setting"
    ) {
        composable(
            route = "main/{text1}/{text2}",
            arguments = listOf(
                navArgument("text1") {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument("text2") {
                    type = NavType.StringType
                }
            )
        ) {
            val text1 = it.arguments?.getString("text1")
            val text2 = it.arguments?.getString("text2")
            MainScreen(navController, text1, text2)
        }
        composable("setting") {
            Button(onClick = { navController.navigate("main/123/321") }) {
                Text(text = "go to main")
            }
        }
    }
}

@Composable
fun MainScreen(
    navController: NavController,
    text1: String?,
    text2: String?
) {
    Column {
        Button(onClick = {
            navController.navigate("setting")
        }) {
            Text(text = "Button")
        }
        Text(text = text1!!)
        Text(text = text2!!)
    }
}