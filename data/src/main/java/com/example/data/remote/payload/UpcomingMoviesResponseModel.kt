package com.example.data.remote.payload

import com.google.gson.annotations.SerializedName

// I'm settings all as nullable because I don't know what can be null or not
class UpcomingMoviesResponseModel(
        @SerializedName("results") val results: List<MovieShortResponseModel>? = null,
        @SerializedName("page") val page: Int? = null,
        @SerializedName("total_pages") val totalPages: Int? = null,
        @SerializedName("total_results") val totalResults: Int? = null
)