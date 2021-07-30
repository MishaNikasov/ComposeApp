package com.example.composeapp.data

import com.example.composeapp.data.model.PhotosResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkApi  {
    @GET("photos")
    suspend fun getPhotos(
        @Query("client_id") clientId: String = "4nurrx0z6LjIjgaD3PFZsNhPyrzgOXGfV8L6BDJjzHY"
    ): Response<ArrayList<PhotosResponseModel>?>
}