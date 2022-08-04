package com.example.xgeekschallenge.data.cb

import com.example.xgeekschallenge.data.model.PhotosSearchResponse

interface DataRetriever {
    fun onDataFetchedSuccess(photos: List<PhotosSearchResponse>)

    fun onDataFetchedFailed()
}