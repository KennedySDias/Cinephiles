package com.example.data.remote.payload

import com.google.gson.annotations.SerializedName

// I'm settings all as nullable because I don't know what can be null or not
class GenresResponseModel(
        @SerializedName("genres") val genres: List<GenresResponseModel>? = null
)