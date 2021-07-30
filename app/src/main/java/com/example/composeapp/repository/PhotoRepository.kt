package com.example.composeapp.repository

import com.example.composeapp.base.BaseRepository
import com.example.composeapp.data.NetworkApi
import com.example.composeapp.mapper.PhotoMapper
import com.example.composeapp.ui.entity.PhotoEntity
import com.example.composeapp.utils.DataState
import javax.inject.Inject

class PhotoRepository @Inject constructor(
    private val networkApi: NetworkApi,
    private val photoMapper: PhotoMapper
): BaseRepository() {
    suspend fun getPhotos(page: Int): DataState<MutableList<PhotoEntity>> {
        return startArrayRequest(
            responseList = networkApi.getPhotos(page),
            mapper = photoMapper
        )
    }
}