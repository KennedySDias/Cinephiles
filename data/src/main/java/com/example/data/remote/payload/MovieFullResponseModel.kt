package com.example.data.remote.payload

import com.google.gson.annotations.SerializedName

// I'm settings all as nullable because I don't know what can be null or not
data class MovieFullResponseModel(
        @SerializedName("adult") val adult: Boolean? = null,
        @SerializedName("backdrop_path") val backdropPath: String? = null,
        @SerializedName("budget") val budget: Long? = null,
        @SerializedName("genres") val genres: List<GenreResponseModel>? = null,
        @SerializedName("homepage") val homepage: String? = null,
        @SerializedName("id") val id: Long? = null,
        @SerializedName("imdb_id") val imdbId: String? = null,
        @SerializedName("original_language") val originalLanguage: String? = null,
        @SerializedName("original_title") val originalTitle: String? = null,
        @SerializedName("overview") val overview: String? = null,
        @SerializedName("popularity") val popularity: Long? = null,
        @SerializedName("poster_path") val posterPath: String? = null,
        @SerializedName("release_date") val releaseDate: String? = null,
        @SerializedName("revenue") val revenue: Int? = null,
        @SerializedName("runtime") val runtime: Int? = null,
        @SerializedName("status") val status: String? = null,
        @SerializedName("tagline") val tagline: String? = null,
        @SerializedName("title") val title: String? = null,
        @SerializedName("video") val video: Boolean? = null,
        @SerializedName("vote_average") val voteAverage: Float? = null,
        @SerializedName("vote_count") val voteCount: Long? = null
)