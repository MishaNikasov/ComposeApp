package com.example.composeapp.ui.navigation

import com.example.composeapp.ui.screen.detail.PHOTO_ID

enum class ScreenId(val id: String) {
    MainId("main_screen"),
    HomeId("home_screen"),
    TopicId("topic_screen"),
    DetailId("detail_screen"),
}

sealed class Screen(val route: String, val routeWithArgs: String = route) {

    object Home: Screen(ScreenId.HomeId.id)
    object Main: Screen(ScreenId.MainId.id)
    object Topic: Screen(ScreenId.TopicId.id)
    object Detail:
        Screen(
            route = ScreenId.DetailId.id,
            routeWithArgs = "${ScreenId.DetailId.id}/{$PHOTO_ID}"
    ) {
        fun putArgs(photoId: String): String {
            return withArgs(photoId)
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
