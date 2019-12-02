package com.example.data.mapper

import com.example.data.model.UpcomingMoviesModel
import com.example.data.remote.payload.UpcomingMoviesResponseModel

class UpcomingMoviesMapper(private val movieMapper: MovieMapper) {

    fun mapPayloadToModel(payload: UpcomingMoviesResponseModel?) = map(payload)

    private fun map(payload: UpcomingMoviesResponseModel?) = UpcomingMoviesModel(
            movies = payload?.results?.map { movieMapper.mapPayloadToModel(it) } ?: emptyList(),
            page = payload?.page ?: 0,
            totalPages = payload?.totalPages ?: 0,
            totalResults = payload?.totalResults ?: 0
    )

}