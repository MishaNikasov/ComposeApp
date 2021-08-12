package com.example.composeapp.ui.test.nav

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composeapp.ui.test.TestViewModel

@Composable
fun ListScreen(
    navController: NavController,
    viewModel: TestViewModel = hiltViewModel()
) {
    val items by viewModel.list.collectAsState()

    Surface (
        modifier = Modifier.fillMaxSize()
    ) {
        Column {

            Button(onClick = {
                viewModel.loadItems()
            }) {
                Text(text = "Load items")
            }

            LazyColumn {
                items.forEach {
                    item {
                        if (it == "Item 36") {
                            Text("OPA HAHOOI")
                        } else {
                            SimpleItem(
                                text = it,
                                onClick = {
                                    navController.navigate("home")
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SimpleItem(
    text: String,
    onClick: () -> Unit = {}
) {
    Card (modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = text
        )
    }
}