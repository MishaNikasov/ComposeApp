package com.example.composeapp.ui.entity

data class SearchEntity (
    val results: List<PhotoEntity>,
    val total: Int,
    val totalPages: Int
)