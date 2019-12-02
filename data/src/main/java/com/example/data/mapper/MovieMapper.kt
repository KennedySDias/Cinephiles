package com.example.data.mapper

import com.example.data.model.FullMovieModel
import com.example.data.model.ShortMovieModel
import com.example.data.remote.payload.MovieFullResponseModel
import com.example.data.remote.payload.MovieShortResponseModel

class MovieMapper(private val genreMapper: GenreMapper) {

    fun mapPayloadToModel(payload: MovieShortResponseModel?) = map(payload)

    private fun map(payload: MovieShortResponseModel?) = ShortMovieModel(
            popularity = payload?.popularity ?: 0f,
            voteCount = payload?.voteCount ?: 0,
            video = payload?.video ?: false,
            posterPath = payload?.posterPath ?: "",
            id = payload?.id ?: 0,
            adult = payload?.adult ?: false,
            backdropPath = payload?.backdropPath ?: "",
            originalLanguage = payload?.originalLanguage ?: "",
            originalTitle = payload?.originalTitle ?: "",
            genreIds = payload?.genreIds ?: emptyList(),
            title = payload?.title ?: "",
            voteAverage = payload?.voteAverage ?: 0f,
            overview = payload?.overview ?: "",
            releaseDate = payload?.releaseDate ?: ""
    )

    fun mapPayloadToModel(payload: MovieFullResponseModel?) = map(payload)

    private fun map(payload: MovieFullResponseModel?) = FullMovieModel(
            adult = payload?.adult ?: false,
            backdropPath = payload?.backdropPath ?: "",
            budget = payload?.budget ?: 0,
            genres = payload?.genres?.map { genreMapper.mapPayloadToModel(it) } ?: emptyList(),
            homepage = payload?.homepage ?: "",
            id = payload?.id ?: 0,
            imdbId = payload?.imdbId ?: "",
            originalLanguage = payload?.originalLanguage ?: "",
            originalTitle = payload?.originalTitle ?: "",
            overview = payload?.overview ?: "",
            popularity = payload?.popularity ?: 0f,
            posterPath = payload?.posterPath ?: "",
            releaseDate = payload?.releaseDate ?: "",
            revenue = payload?.revenue ?: 0,
            runtime = payload?.runtime ?: 0,
            status = payload?.status ?: "",
            tagline = payload?.tagline ?: "",
            title = payload?.title ?: "",
            video = payload?.video ?: false,
            voteAverage = payload?.voteAverage ?: 0f,
            voteCount = payload?.voteCount ?: 0
    )

}