package com.example.composeapp.ui.entity

data class PhotoEntity (
    val id: String,
    val description: String,
    val image: ImageSize,
)

data class ImageSize(
    val full: String,
    val raw: String,
    val regular: String,
    val small: String,
    val thumb: String
)