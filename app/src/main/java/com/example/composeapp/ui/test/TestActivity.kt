package com.example.composeapp.ui.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeapp.ui.test.nav.TestNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestNavigation()
//            TestScreen()

//            MyText()

        }
    }
}

@Composable
fun MyText() {
    Box(
       modifier = Modifier
           .width(12.dp)
           .shadow(
               12.dp,
               RoundedCornerShape(23.dp)
           )
    ) {
        Text(
            text = "Text"
        )
    }
}

@Preview
@Composable
fun MyTextPreview() {
    MyText()
}