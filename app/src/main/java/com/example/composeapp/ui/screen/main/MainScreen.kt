package com.example.composeapp.ui.screen.main

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.composeapp.ui.entity.PhotoEntity
import com.example.composeapp.ui.screen.Screen
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@ExperimentalFoundationApi
@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel
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
                        mainViewModel.deactivateSearching()
                    else
                        mainViewModel.activateSearching(it)
                }
            )
            PhotoList(
                mainViewModel = mainViewModel,
                onPhotoClick = {
                    navController.navigate(Screen.Detail.putArgs(it.id))
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

    val backgroundColor by animateColorAsState(if (isSelected) Color.White else Color(0xFFeeeeee))
    val borderColor by animateColorAsState(if (isSelected) Color(0xFFd1d1d1) else Color.Transparent)

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
            textStyle = TextStyle(
                color = Color(0xFF3D3D3D)
            ),
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
                color = Color(0xFF767676),
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun PhotoList(
    mainViewModel: MainViewModel,
    onPhotoClick: (PhotoEntity) -> Unit
) {
    val entries by mainViewModel.photos.collectAsState()
    val isPaginating by mainViewModel.isPaginating.collectAsState()
    val isSearching by mainViewModel.isSearching.collectAsState()

    LazyVerticalGrid(cells = GridCells.Fixed(2)) {
        items(entries.size) {
            if (it >= entries.size - 1 && !isPaginating) {
                if (isSearching)
                    mainViewModel.searchPhotos()
                else
                    mainViewModel.getPhotos()
            }
            PhotoItem(
                entity = entries[it],
                modifier = Modifier.clickable {
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
    Box(modifier = modifier.aspectRatio(1f)) {
        val painter = rememberImagePainter(
            data = entity.image.small,
            builder = {
                crossfade(true)
            }
        )

        Image(
            painter = painter,
            modifier = Modifier.fillMaxSize(),
            contentDescription = entity.description,
            contentScale = ContentScale.Crop
        )

        when (painter.state) {
            is ImagePainter.State.Loading -> {
                CircularProgressIndicator(
                    color = Color.Black,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .height(40.dp)
                        .width(40.dp)
                )
            }
        }
    }
}
