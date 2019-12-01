package com.example.domain.usecase

import com.example.data.TMDBRepository
import com.example.domain.mapper.MovieMapper
import com.example.domain.model.FullMovieData

class GetMovieDetailsUseCase(
        private val tmdbRepository: TMDBRepository,
        private val movieMapper: MovieMapper
) : UseCase<FullMovieData>() {

    var id: Long = 0

    override suspend fun executeOnBackground(): FullMovieData {
        return movieMapper.mapModelToData(tmdbRepository.getMovieDetails(id))
    }

}