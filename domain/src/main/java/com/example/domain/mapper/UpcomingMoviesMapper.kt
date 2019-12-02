package com.example.domain.mapper

import com.example.data.model.UpcomingMoviesModel
import com.example.domain.model.UpcomingMoviesData

class UpcomingMoviesMapper(private val movieMapper: MovieMapper) {

    fun mapModelToData(model: UpcomingMoviesModel?) = map(model)

    private fun map(model: UpcomingMoviesModel?) = UpcomingMoviesData(
            movies = model?.movies?.map { movieMapper.mapModelToData(it) } ?: emptyList(),
            page = model?.page ?: 0,
            totalPages = model?.totalPages ?: 0,
            totalResults = model?.totalResults ?: 0
    )

}