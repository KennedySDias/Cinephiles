package com.example.domain.usecase

import com.example.data.TMDBRepository
import com.example.domain.mapper.GenreMapper
import com.example.domain.model.GenreData

class GetGenresMoviesUseCase(
        private val tmdbRepository: TMDBRepository,
        private val genreMapper: GenreMapper
) : UseCase<List<GenreData>>() {

    var forceUpdate: Boolean = true

    override suspend fun executeOnBackground(): List<GenreData> {
        // No more business rules here
        return tmdbRepository.getGenresMovies(forceUpdate).map { genreMapper.mapModelToData(it) }
    }

}