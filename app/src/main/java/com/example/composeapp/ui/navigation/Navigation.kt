package com.example.composeapp.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.composeapp.ui.screen.main.MainScreen
import com.example.composeapp.ui.navigation.Screen
import com.example.composeapp.ui.screen.detail.DetailScreen
import com.example.composeapp.ui.screen.detail.PHOTO_ID
import com.example.composeapp.ui.screen.home.HomeViewModel
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalFoundationApi
@InternalCoroutinesApi
@Composable
fun Navigation(
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Main.route
    ) {
        composable(route = Screen.Main.route) {
            MainScreen(
                moveToDetail = {
                    navController.navigate(Screen.Detail.putArgs(photoId = it))
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
                homeViewModel
            )
        }
    }
}