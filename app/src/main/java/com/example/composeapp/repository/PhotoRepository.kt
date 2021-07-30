package com.example.composeapp.repository

import com.example.composeapp.base.BaseRepository
import com.example.composeapp.data.NetworkApi
import com.example.composeapp.data.model.PhotosResponseModel
import com.example.composeapp.utils.DataState
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PhotoRepository @Inject constructor(
    private val networkApi: NetworkApi
): BaseRepository() {
    suspend fun getPhotos(): DataState<ArrayList<PhotosResponseModel>> {
        return startRequest(networkApi.getPhotos())
    }
}