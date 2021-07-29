package com.example.composeapp.ui.screen

import com.example.composeapp.ui.screen.detail.CITY_ID

sealed class Screen(val route: String, val routeWithArgs: String = route) {

    object Main: Screen("main_screen")
    object Detail: Screen("detail_screen", "detail_screen/{$CITY_ID}") {
        fun putArgs(cityId: String): String {
            return withArgs(cityId)
        }
    }

    protected fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
