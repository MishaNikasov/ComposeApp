package com.example.composeapp.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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

        Image(
            painter = painter,
            contentDescription = entity.description,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        )

        Text(
            text = entity.description,
            maxLines = 1,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(5.dp)
                .fillMaxWidth()
        )
    }
}