package com.example.data.remote.payload

import com.google.gson.annotations.SerializedName

// I'm settings all as nullable because I don't know what can be null or not
data class GenreResponseModel(
        @SerializedName("id") val id: Long? = null,
        @SerializedName("name") val name: String? = null
)