package com.example.composeapp.ui.screen.main

import androidx.compose.runtime.mutableStateOf
import com.example.composeapp.base.BaseViewModel
import com.example.composeapp.repository.PhotoRepository
import com.example.composeapp.ui.entity.PhotoEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val photoRepository: PhotoRepository
): BaseViewModel() {

    val photos = mutableStateOf<List<PhotoEntity>>(listOf())

    private var currentPage = 0
    private var isEndReached = mutableStateOf(false)

    init {
        getPhotos()
    }

    fun getPhotos() {
        handleRequest(
            suspend { photoRepository.getPhotos(currentPage) },
            successAction = {
                currentPage++
                photos.value += it ?: listOf()
            }
        )
    }
}