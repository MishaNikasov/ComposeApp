package com.example.composeapp.ui.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.composeapp.ui.screen.main.MainViewModel
import com.google.accompanist.imageloading.rememberDrawablePainter
import timber.log.Timber

const val PHOTO_ID = "photo_id"

@Composable
fun DetailScreen(
    navController: NavController,
    photoId: String?,
    mainViewModel: MainViewModel
) {
    Box (
        modifier = Modifier.fillMaxSize()
    ){
        val photoEntity = mainViewModel.photos.value.first { it.id == photoId }
        val painter = rememberImagePainter(
            data = photoEntity.image.full,
            builder = {
                crossfade(true)
            }
        )

        Image(
            painter = painter,
            contentDescription = photoEntity.description,
            modifier = Modifier.fillMaxSize()
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