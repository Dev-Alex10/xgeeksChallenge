package com.example.xgeekschallenge.data.cb

import com.example.xgeekschallenge.data.model.Photo

interface DataRetriever {
    fun onDataFetchedSuccess(photos: List<Photo>)

    fun onDataFetchedFailed()
}