package com.example.composeapp.data

import com.example.composeapp.data.model.PhotosResponseModel
import com.example.composeapp.data.model.search.SearchResultResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkApi  {
    @GET("photos")
    suspend fun getPhotos(
        @Query("page") page: Int,
        @Query("client_id") clientId: String = "4nurrx0z6LjIjgaD3PFZsNhPyrzgOXGfV8L6BDJjzHY"
    ): Response<List<PhotosResponseModel>?>

    @GET("search/photos")
    suspend fun searchPhotos(
        @Query("page") page: Int,
        @Query("query") query: String,
        @Query("client_id") clientId: String = "4nurrx0z6LjIjgaD3PFZsNhPyrzgOXGfV8L6BDJjzHY"
    ): Response<SearchResultResponseModel?>
}