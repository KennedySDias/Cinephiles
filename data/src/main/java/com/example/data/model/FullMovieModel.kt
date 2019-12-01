package com.example.data.model

data class FullMovieModel(
        val adult: Boolean,
        val backdropPath: String,
        val budget: Long?,
        val genres: List<GenreModel>,
        val homepage: String,
        val id: Long,
        val imdbId: String,
        val originalLanguage: String,
        val originalTitle: String,
        val overview: String,
        val popularity: Long,
        val posterPath: String,
        val releaseDate: String,
        val revenue: Int,
        val runtime: Int,
        val status: String,
        val tagline: String,
        val title: String,
        val video: Boolean,
        val voteAverage: Float,
        val voteCount: Long
)