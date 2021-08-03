package com.example.composeapp.ui.screen.detail

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import com.example.composeapp.ui.screen.home.HomeViewModel
import com.example.composeapp.ui.view.CoilImage

const val PHOTO_ID = "photo_id"

@Composable
fun DetailScreen(
    photoId: String?,
    homeViewModel: HomeViewModel
) {
    val photoEntity = homeViewModel.photos.value.first { it.id == photoId }

    CoilImage(
        image = photoEntity.image.full,
        contentDescription = photoEntity.description,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillWidth
    )
}