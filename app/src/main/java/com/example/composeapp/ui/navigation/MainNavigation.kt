package com.example.composeapp.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navigation
import com.example.composeapp.ui.screen.Screen
import com.example.composeapp.ui.screen.detail.PHOTO_ID
import com.example.composeapp.ui.screen.detail.DetailScreen
import com.example.composeapp.ui.screen.main.MainScreen
import com.example.composeapp.ui.screen.main.MainViewModel
import com.example.composeapp.ui.screen.topics.TopicScreen
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@ExperimentalFoundationApi
@Composable
fun MainNavigation(
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val items = listOf(
        Screen.Main,
        Screen.Topic
    )
    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(Icons.Outlined.Home, contentDescription = null) },
                        label = { Text(text = "ad") },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }) {
            NavHost(
                navController = navController,
                startDestination = Screen.Main.route
            ) {
                composable(
                    route = Screen.Main.route
                ) {
                    MainScreen(
                        navController = navController,
                        mainViewModel
                    )
                }
                composable(route = Screen.Topic.route) {
                    TopicScreen()
                }
            }
    }
}

//NavHost(
//navController = navController,
//startDestination = Screen.Topic.route
//) {
//    navigation(
//        startDestination = Screen.Main.route,
//        route = Screen.Main.route,
//    ) {
//
//        composable(
//            route = Screen.Main.route
//        ) {
//            MainScreen(
//                navController = navController,
//                mainViewModel
//            )
//        }
//        composable(
//            route = Screen.Detail.routeWithArgs,
//            arguments = listOf(
//                navArgument(PHOTO_ID) {
//                    type = NavType.StringType
//                }
//            )
//        ) { entry ->
//            DetailScreen(
//                navController = navController,
//                photoId = entry.arguments?.getString(PHOTO_ID),
//                mainViewModel
//            )
//        }
//    }
//
//    composable(route = Screen.Topic.route) {
//        TopicScreen()
//    }
//}