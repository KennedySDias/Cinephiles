package com.example.domain.model

data class ShortMovieData(
        val popularity: Float,
        val voteCount: Int,
        val video: Boolean,
        val posterPath: String,
        val id: Long,
        val adult: Boolean,
        val backdropPath: String,
        val originalLanguage: String,
        val originalTitle: String,
        val genreIds: List<Long>,
        val title: String,
        val voteAverage: Float,
        val overview: String,
        val releaseDate: String
)