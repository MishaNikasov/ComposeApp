package com.example.composeapp.ui.screen

import com.example.composeapp.ui.screen.detail.CITY_ID

enum class ScreenId(val id: String) {
    MainId("main_screen"),
    DetailId("detail_screen"),
}

sealed class Screen(val route: String, val routeWithArgs: String = route) {

    object Main: Screen(ScreenId.MainId.id)
    object Detail: Screen(ScreenId.DetailId.id, "${ScreenId.DetailId.id}/{$CITY_ID}") {
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
