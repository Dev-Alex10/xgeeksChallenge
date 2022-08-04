package com.example.xgeekschallenge.data.persistence.Photo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Photo")
data class PhotoModel(
    @PrimaryKey(autoGenerate = false)
    val id : String,
    @ColumnInfo(name = "url")
    val url :String,
    @ColumnInfo(name = "title")
    val title :String,

)