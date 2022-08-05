package com.example.xgeekschallenge.data

import com.example.xgeekschallenge.data.model.PhotosSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosAPI {
    @GET("?method=flickr.photos.search&format=json&safe_search=1&content_type=6&nojsoncallback=1&api_key=$API_KEY")
    suspend fun fetchImages(@Query(value = "text") searchTerm: String): PhotosSearchResponse

    @GET("?method=flickr.photos.getRecent&format=json&nojsoncallback=1&api_key=$API_KEY")
    suspend fun fetchRecentImages(): PhotosSearchResponse

}