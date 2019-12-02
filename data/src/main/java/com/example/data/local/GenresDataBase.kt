package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.local.dao.GenresDao
import com.example.data.local.entity.GenreCache

@Database(entities = [GenreCache::class], version = 1)
abstract class GenresDataBase : RoomDatabase() {
    abstract fun genresDao(): GenresDao

    companion object {
        fun createDataBase(context: Context): GenresDao {
            return Room
                    .databaseBuilder(context, GenresDataBase::class.java, "Genres.db")
                    .build()
                    .genresDao()
        }
    }
}