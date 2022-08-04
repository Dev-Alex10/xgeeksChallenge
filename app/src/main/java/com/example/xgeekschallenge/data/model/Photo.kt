package com.example.xgeekschallenge.data.model

import com.google.gson.annotations.SerializedName

//will be needed to show the photo on layout
data class Photo(
    val id: String,
    val url: String,
    val title: String
)

data class PhotosSearchResponse(
    @SerializedName("photos") //API name
    val requestMetaData: RequestMetaData
)

//object with array
data class RequestMetaData(
    @SerializedName("page")
    val page: Int,
    @SerializedName("photo")
    val photos: List<PhotoResponse>
)

//array in response without the is public/friend/family, because all we can use are public
data class PhotoResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("owner")
    val owner: String,
    @SerializedName("secret")
    val secret: String,
    @SerializedName("server")
    val server: String,
    @SerializedName("title")
    val title: String
)

data class PhotoMetaData(
    @SerializedName("id")
    val id: String,
    @SerializedName("secret")
    val secret: String,
    @SerializedName("server")
    val server: String,
    @SerializedName("dateuploaded")
    val dateUploaded: String,
    @SerializedName("originalformat")
    val originalFormat: String
)

fun PhotoResponse.toDomain(): Photo {
    return Photo(
        id = id,
        url = "https://live.staticflickr.com/${server}/${id}_${secret}.jpg",
        title = title
    )
}