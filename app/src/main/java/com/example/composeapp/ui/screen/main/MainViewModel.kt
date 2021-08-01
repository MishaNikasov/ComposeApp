package com.example.composeapp.ui.screen.main

import com.example.composeapp.base.BaseViewModel
import com.example.composeapp.repository.PhotoRepository
import com.example.composeapp.ui.entity.PhotoEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val photoRepository: PhotoRepository
): BaseViewModel() {

    private val _photos = MutableStateFlow<List<PhotoEntity>>(listOf())
    val photos = _photos

    val isPaginating = MutableStateFlow(false)
    val isSearching = MutableStateFlow(false)

    private val startingPageForPagination = 1

    private val searchQuery = MutableStateFlow("")

    private val currentPage = MutableStateFlow(startingPageForPagination)
    private val isEndReached = MutableStateFlow(false)

    init {
        getPhotos()
    }

    fun getPhotos() {
        isPaginating.value = true
        handleRequest(
            suspend { photoRepository.getPhotos(currentPage.value) },
            successAction = {
                _photos.value += it ?: listOf()

                currentPage.value++
                isPaginating.value = false
            }
        )
    }

    fun searchPhotos() {
        isPaginating.value = true
        handleRequest(
            suspend { photoRepository.searchPhotos(currentPage.value, searchQuery.value) },
            successAction = {
                _photos.value += it?.results ?: listOf()

                currentPage.value++
                isPaginating.value = false
            }
        )
    }

    fun activateSearching(query: String) {
        currentPage.value = startingPageForPagination
        _photos.value = mutableListOf()
        isSearching.value = true
        searchQuery.value = query
        searchPhotos()
    }

    fun deactivateSearching() {
        currentPage.value = startingPageForPagination
        _photos.value = mutableListOf()
        isSearching.value = false
        searchQuery.value = ""
        getPhotos()
    }
}