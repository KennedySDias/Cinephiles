package com.example.data.local.dao

import androidx.room.*
import com.example.data.local.entity.GenreCache

@Dao
interface GenresDao {

    @Query(value = "SELECT * FROM genres")
    suspend fun getAll(): List<GenreCache>

    @Insert
    suspend fun insert(breed: GenreCache)

    @Insert
    suspend fun insertAll(breeds: List<GenreCache>)

    @Transaction
    suspend fun update(breeds: List<GenreCache>) {
        deleteAll()
        insertAll(breeds)
    }

    @Delete
    suspend fun delete(breed: GenreCache)

    @Query("DELETE FROM genres")
    suspend fun deleteAll()

}