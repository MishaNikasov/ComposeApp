package com.example.composeapp.data.model.search

import com.example.composeapp.data.model.PhotosResponseModel

data class SearchResultResponseModel(
    val results: List<PhotosResponseModel>,
    val total: Int,
    val total_pages: Int
)