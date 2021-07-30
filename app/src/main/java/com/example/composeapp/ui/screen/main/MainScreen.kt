package com.example.composeapp.ui.screen.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composeapp.ui.view.PhotoItem
import com.example.composeapp.ui.view.SearchBar

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
                    if (it.isEmpty())
                        mainViewModel.deactivateSearching()
                    else
                        mainViewModel.activateSearching(it)
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
    val isPaginating by remember { mainViewModel.isPaginating }
    val isSearching by remember { mainViewModel.isSearching }

    LazyColumn {
        items(entries.size) {
            if (it >= entries.size - 1 && !isPaginating) {
                if (isSearching)
                    mainViewModel.searchPhotos()
                else
                    mainViewModel.getPhotos()
            }
            PhotoItem(entity = entries[it])
        }
    }
}