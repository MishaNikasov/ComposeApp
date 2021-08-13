package com.example.composeapp.ui.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.composeapp.ui.test.navigation.TestNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestNavigation()
//            TestNavigation()
//            TestScreen()
        }
    }
}