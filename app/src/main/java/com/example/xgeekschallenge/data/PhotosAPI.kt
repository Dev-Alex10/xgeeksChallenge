package com.example.xgeekschallenge.data

import com.example.xgeekschallenge.data.model.PhotosSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosAPI {
    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1&api_key=$API_KEY")
    suspend fun fetchImages(@Query(value = "text") searchTerm: String): PhotosSearchResponse
    @GET("?method=flickr.photos.getRecent&format=json&nojsoncallback=1&api_key=$API_KEY")
    suspend fun fetchRecentImages(@Query(value = "text") searchTerm: String): PhotosSearchResponse
    @GET("?method=flickr.photos.getPopular&format=json&nojsoncallback=1&api_key=$API_KEY")
    suspend fun fetchPopularImages(@Query(value = "text") searchTerm: String): PhotosSearchResponse

    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1&api_key=$API_KEY")
    fun getList(): Call<List<PhotosSearchResponse>>

    //@GET(server/id_secret.jpg)
}