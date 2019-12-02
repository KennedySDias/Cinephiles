package com.example.domain.mapper

import com.example.data.model.FullMovieModel
import com.example.data.model.ShortMovieModel
import com.example.domain.model.FullMovieData
import com.example.domain.model.ShortMovieData

class MovieMapper(private val genreMapper: GenreMapper) {

    fun mapModelToData(model: ShortMovieModel?) = map(model)

    private fun map(model: ShortMovieModel?) = ShortMovieData(
            popularity = model?.popularity ?: 0f,
            voteCount = model?.voteCount ?: 0,
            video = model?.video ?: false,
            posterPath = model?.posterPath ?: "",
            id = model?.id ?: 0,
            adult = model?.adult ?: false,
            backdropPath = model?.backdropPath ?: "",
            originalLanguage = model?.originalLanguage ?: "",
            originalTitle = model?.originalTitle ?: "",
            genreIds = model?.genreIds ?: emptyList(),
            title = model?.title ?: "",
            voteAverage = model?.voteAverage ?: 0f,
            overview = model?.overview ?: "",
            releaseDate = model?.releaseDate ?: ""
    )

    fun mapModelToData(model: FullMovieModel?) = map(model)

    private fun map(model: FullMovieModel?) = FullMovieData(
            adult = model?.adult ?: false,
            backdropPath = model?.backdropPath ?: "",
            budget = model?.budget ?: 0,
            genres = model?.genres?.map { genreMapper.mapModelToData(it) } ?: emptyList(),
            homepage = model?.homepage ?: "",
            id = model?.id ?: 0,
            imdbId = model?.imdbId ?: "",
            originalLanguage = model?.originalLanguage ?: "",
            originalTitle = model?.originalTitle ?: "",
            overview = model?.overview ?: "",
            popularity = model?.popularity ?: 0f,
            posterPath = model?.posterPath ?: "",
            releaseDate = model?.releaseDate ?: "",
            revenue = model?.revenue ?: 0,
            runtime = model?.runtime ?: 0,
            status = model?.status ?: "",
            tagline = model?.tagline ?: "",
            title = model?.title ?: "",
            video = model?.video ?: false,
            voteAverage = model?.voteAverage ?: 0f,
            voteCount = model?.voteCount ?: 0
    )

}