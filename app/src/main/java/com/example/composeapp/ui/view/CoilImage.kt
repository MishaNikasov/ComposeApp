package com.example.composeapp.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest


@ExperimentalCoilApi
@Composable
fun CoilImage(
    image: String,
    contentDescription: String,
    modifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier.fillMaxSize(),
    builder: ImageRequest.Builder.() -> Unit = {
        crossfade(true)
    },
    contentScale: ContentScale = ContentScale.Crop,
    loader: @Composable BoxScope.() -> Unit = {
        CircularProgressIndicator(
            color = Color.Black,
            modifier = Modifier
                .height(40.dp)
                .width(40.dp)
                .align(Alignment.Center)
        )
    }
) {
    Box(
        modifier = modifier
    ) {

        val painter = rememberImagePainter(
            data = image,
            builder = builder
        )

        Image(
            painter = painter,
            modifier = imageModifier,
            contentDescription = contentDescription,
            contentScale = contentScale
        )

        when (painter.state) {
            is ImagePainter.State.Loading -> {
                loader()
            }
            is ImagePainter.State.Error -> { }
            ImagePainter.State.Empty -> { }
            is ImagePainter.State.Success -> { }
        }
    }
}