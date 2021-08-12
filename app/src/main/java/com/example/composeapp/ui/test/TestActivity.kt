package com.example.composeapp.ui.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            
            Column {
                Text(text = "opa")
            }
            
            TestScreen()
        }
    }
}