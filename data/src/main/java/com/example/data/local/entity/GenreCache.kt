package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genres")
data class GenreCache(
        @PrimaryKey(autoGenerate = true) val id: Long? = null,
        val name: String
)