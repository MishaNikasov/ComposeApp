package com.example.composeapp.repository

import com.example.composeapp.base.BaseRepository
import com.example.composeapp.data.NetworkApi
import com.example.composeapp.data.model.search.SearchResultResponseModel
import com.example.composeapp.mapper.PhotoMapper
import com.example.composeapp.mapper.SearchMapper
import com.example.composeapp.ui.entity.PhotoEntity
import com.example.composeapp.ui.entity.SearchEntity
import com.example.composeapp.utils.DataState
import javax.inject.Inject

class PhotoRepository @Inject constructor(
    private val networkApi: NetworkApi,
    private val photoMapper: PhotoMapper,
    private val searchMapper: SearchMapper
): BaseRepository() {

    suspend fun getPhotos(page: Int): DataState<MutableList<PhotoEntity>> {
        return startArrayRequest(
            responseList = networkApi.getPhotos(page),
            mapper = photoMapper
        )
    }

    suspend fun searchPhotos(page: Int, query: String): DataState<SearchEntity> {
        return startRequest(
            response = networkApi.searchPhotos(page, query),
            mapper = searchMapper
        )
    }
}