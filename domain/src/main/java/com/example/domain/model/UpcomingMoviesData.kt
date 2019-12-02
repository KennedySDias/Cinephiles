package com.example.domain.model

data class UpcomingMoviesData(
        val movies: List<ShortMovieData>,
        val page: Int,
        val totalPages: Int,
        val totalResults: Int
)