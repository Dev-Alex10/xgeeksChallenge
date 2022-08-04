package com.example.xgeekschallenge.data.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.xgeekschallenge.data.persistence.Photo.PhotoDao
import com.example.xgeekschallenge.data.persistence.Photo.PhotoModel
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Database(entities = [PhotoModel::class], version = 1, exportSchema = false)
abstract class KennelDatabase : RoomDatabase() {

    abstract fun photoDao(): PhotoDao

    companion object {
        @Volatile
        private var INSTANCE: KennelDatabase? = null

        fun getDatabase(context: Context): KennelDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    KennelDatabase::class.java,
                    "photo_database"
                ).build()
                INSTANCE = instance

                instance
            }
        }
        val databaseWriterExecutor: ExecutorService = Executors.newFixedThreadPool(2)
    }
}