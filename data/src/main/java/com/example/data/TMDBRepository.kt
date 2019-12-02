package com.example.data

import com.example.data.model.FullMovieModel
import com.example.data.model.GenreModel
import com.example.data.model.UpcomingMoviesModel

interface TMDBRepository {

    suspend fun getGenresMovies(forceUpdate: Boolean): List<GenreModel>

    suspend fun getUpcomingMovies(page: Int): UpcomingMoviesModel

    suspend fun getMovieDetails(id: Long): FullMovieModel
}

class TMDBRepositoryImpl(
        private val cacheDataSource: CacheDataSource,
        private val remoteDataSource: RemoteDataSource
) : TMDBRepository {

    private suspend fun getGenresFromRemote(forceUpdate: Boolean): List<GenreModel> {
        return remoteDataSource.getGenresMovies()
                .let { list ->
                    if (forceUpdate) {
                        cacheDataSource.update(list)
                    }

                    list
                }
    }

    override suspend fun getGenresMovies(forceUpdate: Boolean): List<GenreModel> {
        return if (forceUpdate) {
            // Get data from Remote and save in Cache
            getGenresFromRemote(true)
        } else {
            // Get data from Cache and return them, if the cache is empty, so get from Remote
            cacheDataSource.getAll()
                    .let { list ->
                        when {
                            list.isEmpty() -> getGenresFromRemote(true)
                            else -> list
                        }
                    }
        }
    }

    override suspend fun getUpcomingMovies(page: Int): UpcomingMoviesModel {
        return remoteDataSource.getUpcomingMovies(page)
    }

    override suspend fun getMovieDetails(id: Long): FullMovieModel {
        return remoteDataSource.getMovieDetails(id)
    }

}