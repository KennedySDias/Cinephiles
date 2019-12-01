package com.example.data.model

data class UpcomingMoviesModel(
        val movies: List<ShortMovieModel>,
        val page: Int,
        val totalPages: Int,
        val totalResults: Int
)