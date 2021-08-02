package com.example.composeapp.ui.controller

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.composeapp.ui.navigation.MainNavigation
import com.example.composeapp.ui.screen.Screen
import com.example.composeapp.ui.screen.detail.DetailScreen
import com.example.composeapp.ui.screen.detail.PHOTO_ID
import com.example.composeapp.ui.screen.main.MainViewModel
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalFoundationApi
@InternalCoroutinesApi
@Composable
fun MainController(
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Main.route
    ) {
        composable(route = Screen.Main.route) {
            MainNavigation(
                moveToDetail = {
                    navController.navigate(Screen.Detail.routeWithArgs)
                }
            )
        }

        composable(
            route = Screen.Detail.routeWithArgs,
            arguments = listOf(
                navArgument(PHOTO_ID) {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            DetailScreen(
                navController = navController,
                photoId = entry.arguments?.getString(PHOTO_ID),
                mainViewModel
            )
        }
    }
}