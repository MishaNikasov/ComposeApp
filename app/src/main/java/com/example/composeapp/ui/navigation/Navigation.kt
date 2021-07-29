package com.example.composeapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.composeapp.ui.screen.Screen
import com.example.composeapp.ui.screen.detail.CITY_ID
import com.example.composeapp.ui.screen.detail.DetailScreen
import com.example.composeapp.ui.screen.main.MainScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Main.route) {
        composable(route = Screen.Main.route) {
            MainScreen(navController = navController)
        }
        composable(
            route = Screen.Detail.routeWithArgs,
            arguments = listOf(
                navArgument(CITY_ID) {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            DetailScreen(navController = navController, cityId = entry.arguments?.getString(CITY_ID))
        }
    }
}