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

    private val _photos = mutableStateOf<List<PhotoEntity>>(listOf())
    val photos = _photos

    val searchQuery = mutableStateOf("")

    val isPaginating = mutableStateOf(false)
    val isSearching = mutableStateOf(false)

    private var currentPage = 0
    private var isEndReached = mutableStateOf(false)

    init {
        getPhotos()
    }

    fun getPhotos() {
        isPaginating.value = true
        handleRequest(
            suspend { photoRepository.getPhotos(currentPage) },
            successAction = {
                currentPage++
                isPaginating.value = false

                _photos.value += it ?: listOf()
            }
        )
    }

    fun searchPhotos() {
        isPaginating.value = true
        isSearching.value = true
        handleRequest(
            suspend { photoRepository.searchPhotos(currentPage, searchQuery.value) },
            successAction = {
                _photos.value += it?.results ?: listOf()

                currentPage++
                isPaginating.value = false
            }
        )
    }

    fun activateSearching(query: String) {
        currentPage = 0
        _photos.value = mutableListOf()
        isSearching.value = true
        searchQuery.value = query
        searchPhotos()
    }

    fun deactivateSearching() {
        currentPage = 0
        _photos.value = mutableListOf()
        isSearching.value = false
        searchQuery.value = ""
        getPhotos()
    }
}