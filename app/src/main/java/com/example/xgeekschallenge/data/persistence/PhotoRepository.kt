package com.example.xgeekschallenge.data.persistence

import androidx.lifecycle.LiveData

class PhotoRepository(private val photoDao: PhotoDao) {
    val allPhotos : LiveData<List<PhotoModel>> = photoDao.getAllPhotos()
}