package com.example.composeapp.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.composeapp.ui.entity.PhotoEntity

@Composable
fun PhotoItem(
    entity: PhotoEntity,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        val painter = rememberImagePainter(
            data = entity.image.small,
            builder = {
                crossfade(true)
            }
        )

        val verticalGradientBrush = Brush.verticalGradient(
            colors = listOf(Color.Transparent, Color.Black)
        )

        Image(
            painter = painter,
            contentDescription = entity.description,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )

        Box (
            Modifier
                .fillMaxWidth()
                .height(35.dp)
                .background(verticalGradientBrush)
                .align(Alignment.BottomCenter)
        )

        Text(
            text = entity.description,
            maxLines = 1,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(5.dp)
                .fillMaxWidth()
        )

        when (painter.state) {
            is ImagePainter.State.Loading -> {
                CircularProgressIndicator(modifier = Modifier
                    .align(Alignment.Center)
                    .height(30.dp)
                    .width(30.dp)
                )
            }
        }
    }
}