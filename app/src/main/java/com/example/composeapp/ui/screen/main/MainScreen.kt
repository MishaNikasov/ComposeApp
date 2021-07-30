package com.example.composeapp.ui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.composeapp.ui.view.PhotoItem
import com.example.composeapp.ui.view.SearchBar
import timber.log.Timber

@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel()
) {

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                hint = "Search",
                onSearch = {

                }
            )
            PhotoList(mainViewModel = mainViewModel)
        }
    }
}

@Composable
fun PhotoList(
    mainViewModel: MainViewModel
) {
    val entries by remember { mainViewModel.photos }

    LazyColumn {
        items(entries.size) {
            if (it >= entries.size - 1) {
                mainViewModel.getPhotos()
            }
            PhotoItem(entity = entries[it])
        }
    }
}