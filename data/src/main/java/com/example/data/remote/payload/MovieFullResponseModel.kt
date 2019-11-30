package com.example.data.remote.payload

import com.google.gson.annotations.SerializedName

// I'm settings all as nullable because I don't know what can be null or not
class MovieFullResponseModel(
        @SerializedName("adult") val adult: Boolean? = null,
        @SerializedName("backdrop_path") val backdropPath: String? = null,
        @SerializedName("budget") val budget: Long? = null,
        @SerializedName("genres") val genres: List<GenreResponseModel>? = null,
        @SerializedName("homepage") val homepage: String? = null,
        @SerializedName("id") val id: Long,
        @SerializedName("imdb_id") val imdbId: String,
        @SerializedName("original_language") val originalLanguage: String,
        @SerializedName("original_title") val originalTitle: String,
        @SerializedName("overview") val overview: String,
        @SerializedName("popularity") val popularity: Long,
        @SerializedName("poster_path") val posterPath: String,
        @SerializedName("release_date") val releaseDate: String,
        @SerializedName("revenue") val revenue: Int,
        @SerializedName("runtime") val runtime: Int,
        @SerializedName("status") val status: String,
        @SerializedName("tagline") val tagline: String,
        @SerializedName("title") val title: String,
        @SerializedName("video") val video: Boolean,
        @SerializedName("vote_average") val voteAverage: Float,
        @SerializedName("vote_count") val voteCount: Long
)