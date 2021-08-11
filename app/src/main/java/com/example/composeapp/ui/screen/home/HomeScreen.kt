package com.example.composeapp.ui.screen.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.composeapp.ui.entity.PhotoEntity
import com.example.composeapp.ui.view.CoilImage
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@ExperimentalFoundationApi
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    moveToDetail: (String) -> Unit
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
                    .padding(12.dp),
                hint = "Search",
                onSearch = {
                    if (it.isEmpty())
                        homeViewModel.deactivateSearching()
                    else
                        homeViewModel.activateSearching(it)
                }
            )
            PhotoList(
                homeViewModel = homeViewModel,
                onPhotoClick = {
                    moveToDetail(it.id)
                }
            )
        }
    }
}

@InternalCoroutinesApi
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {
    var text by remember {
        mutableStateOf("")
    }
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }
    var isSelected by remember {
        mutableStateOf(false)
    }

    val backgroundColor by animateColorAsState(if (isSelected) MaterialTheme.colors.background else MaterialTheme.colors.secondary)
    val borderColor by animateColorAsState(if (isSelected) MaterialTheme.colors.onSecondary else Color.Transparent)

    Card(
        shape = CircleShape,
        elevation = 0.dp,
        border = BorderStroke(1.dp, borderColor),
        backgroundColor = backgroundColor,
        modifier = modifier
    ) {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                isHintDisplayed = it.isEmpty()
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = MaterialTheme.typography.h1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    isSelected = it.isFocused
                }
        )
        if (isHintDisplayed) {
            Text(
                text = hint,
                style = MaterialTheme.typography.h2,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun PhotoList(
    homeViewModel: HomeViewModel,
    onPhotoClick: (PhotoEntity) -> Unit
) {
    val entries by homeViewModel.photos.collectAsState()
    val isPaginating by homeViewModel.isPaginating.collectAsState()
    val isSearching by homeViewModel.isSearching.collectAsState()

    LazyVerticalGrid(cells = GridCells.Fixed(2)) {
        items(entries.size) {
            if (it >= entries.size - 1 && !isPaginating) {
                if (isSearching)
                    homeViewModel.searchPhotos()
                else
                    homeViewModel.getPhotos()
            }
            PhotoItem(
                entity = entries[it],
                modifier = Modifier
                    .aspectRatio(1f)
                    .clickable {
                        onPhotoClick(entries[it])
                    }
            )
        }
    }
}

@Composable
fun PhotoItem(
    entity: PhotoEntity,
    modifier: Modifier
) {
    CoilImage(
        image = entity.image.small,
        contentDescription = entity.description,
        modifier = modifier
    )
}