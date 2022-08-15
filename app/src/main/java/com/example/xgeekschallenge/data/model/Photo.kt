package com.example.xgeekschallenge.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

//will be needed to show the photo on layout
@Parcelize
data class Photo(
    val id: String,
    val url: String,
    val title: String,
    val dateUploaded: String,
    val dateTaken: String,
    val owner: String,
    val width: String?,
    val height: String?
) : Parcelable

data class PhotosSearchResponse(
    @SerializedName("photos") //API name
    val requestMetaData: RequestMetaData
)

//object with array
data class RequestMetaData(
    @SerializedName("page")
    val page: Int,
    @SerializedName("photo")
    val photosInfo: List<PhotoResponse>
)

//array in response without the is public/friend/family, because all we can use are public
data class PhotoResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("secret")
    val secret: String,
    @SerializedName("server")
    val server: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("dateupload")
    val dateUploaded: String,
    @SerializedName("datetaken")
    val dateTaken: String,
    @SerializedName("ownername")
    val owner: String,
    @SerializedName("o_width")
    val width: String,
    @SerializedName("o_height")
    val height: String
)

fun PhotoResponse.toDomain(): Photo {
    return Photo(
        id = id,
        url = "https://live.staticflickr.com/${server}/${id}_${secret}.jpg",//no _size its the default max edge 500px
        title = title,
        dateUploaded = dateUploaded,
        dateTaken = dateTaken,
        owner = owner,
        width = width,
        height = height
    )
}