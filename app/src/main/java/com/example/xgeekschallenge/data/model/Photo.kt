package com.example.xgeekschallenge.data.model

//will be needed to show the photo on layout
data class Photo(
    val id: String,
    val url: String,
    val title: String
)
data class PhotosSearchResponse(
    val photos: PhotosMetaData
)
//object with array
data class PhotosMetaData(
    val page: Int,
    val photo: List<PhotoResponse>
)
//array in response without the is public/friend/family, because all we can use are public
data class PhotoResponse(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Int,
    val title: String
)