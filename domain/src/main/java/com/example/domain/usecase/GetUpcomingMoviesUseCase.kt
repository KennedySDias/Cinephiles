package com.example.domain.usecase

import com.example.data.TMDBRepository
import com.example.domain.mapper.UpcomingMoviesMapper
import com.example.domain.model.UpcomingMoviesData

class GetUpcomingMoviesUseCase(
        private val upcomingMoviesMapper: UpcomingMoviesMapper,
        private val tmdbRepository: TMDBRepository
) : UseCase<UpcomingMoviesData>() {

    var page: Long = 1

    override suspend fun executeOnBackground(): UpcomingMoviesData {
        return upcomingMoviesMapper.mapModelToData(tmdbRepository.getUpcomingMovies(page))
    }

}