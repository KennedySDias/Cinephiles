package com.example.data.remote.payload

import com.google.gson.annotations.SerializedName

// I'm settings all as nullable because I don't know what can be null or not
data class MovieShortResponseModel(
        @SerializedName("popularity") val popularity: Float? = null,
        @SerializedName("vote_count") val voteCount: Int? = null,
        @SerializedName("video") val video: Boolean? = null,
        @SerializedName("poster_path") val posterPath: String? = null,
        @SerializedName("id") val id: Long? = null,
        @SerializedName("adult") val adult: Boolean? = null,
        @SerializedName("backdrop_path") val backdropPath: String? = null,
        @SerializedName("original_language") val originalLanguage: String? = null,
        @SerializedName("original_title") val originalTitle: String? = null,
        @SerializedName("genre_ids") val genreIds: List<Long>? = null,
        @SerializedName("title") val title: String? = null,
        @SerializedName("vote_average") val voteAverage: Float? = null,
        @SerializedName("overview") val overview: String? = null,
        @SerializedName("release_date") val releaseDate: String? = null
)