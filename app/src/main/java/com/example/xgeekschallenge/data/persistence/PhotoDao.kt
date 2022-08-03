package com.example.xgeekschallenge.data.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface PhotoDao {
    @Query("SELECT * FROM Photo")
    fun getAllPhotos(): LiveData<List<PhotoModel>>
}