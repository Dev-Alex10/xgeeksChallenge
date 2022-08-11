package com.example.xgeekschallenge.data

import com.example.xgeekschallenge.data.model.PhotoResponse
import com.example.xgeekschallenge.data.model.PhotosSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosAPI {
    //type 6 = photos and other
    @GET("?method=flickr.photos.search&format=json&safe_search=1&content_type=6&nojsoncallback=1&api_key=$API_KEY&extras=date_upload,date_taken,o_dims,owner_name")
    suspend fun fetchImages(@Query(value = "text") searchTerm: String): PhotosSearchResponse

    //nojsoncallback = object not starting with FlickrApi
    @GET("?method=flickr.photos.getRecent&format=json&nojsoncallback=1&api_key=$API_KEY&extras=date_upload,date_taken,o_dims,owner_name")
    suspend fun fetchRecentImages(): PhotosSearchResponse

}