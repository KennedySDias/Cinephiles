package com.example.data

import com.example.data.mapper.GenreMapper
import com.example.data.mapper.MovieMapper
import com.example.data.mapper.UpcomingMoviesMapper
import com.example.data.model.FullMovieModel
import com.example.data.model.GenreModel
import com.example.data.model.UpcomingMoviesModel
import com.example.data.remote.RetrofitBuilder
import com.example.data.remote.api.TMDBApi
import java.util.*

interface RemoteDataSource {

    suspend fun getGenresMovies(): List<GenreModel>

    suspend fun getUpcomingMovies(page: Int): UpcomingMoviesModel

    suspend fun getMovieDetails(id: Long): FullMovieModel

}

class RemoteDataSourceImpl(
        private val retrofitBuilder: RetrofitBuilder,
        private val genreMapper: GenreMapper,
        private val movieMapper: MovieMapper,
        private val upcomingMoviesMapper: UpcomingMoviesMapper
) : RemoteDataSource {

    override suspend fun getGenresMovies(): List<GenreModel> {
        return retrofitBuilder
                .build(TMDBApi::class.java)
                .fetchGenresMovies(
                        apiKey = BuildConfig.API_KEY,
                        language = Locale.getDefault().language
                ).genres?.map { genreMapper.mapPayloadToModel(it) } ?: emptyList()
    }

    override suspend fun getUpcomingMovies(page: Int): UpcomingMoviesModel {
        return upcomingMoviesMapper.mapPayloadToModel(retrofitBuilder
                .build(TMDBApi::class.java)
                .fetchUpcomingMovies(
                        apiKey = BuildConfig.API_KEY,
                        language = Locale.getDefault().language,
                        page = page,
                        region = Locale.getDefault().country
                ))
    }

    override suspend fun getMovieDetails(id: Long): FullMovieModel {
        return movieMapper.mapPayloadToModel(retrofitBuilder
                .build(TMDBApi::class.java)
                .fetchMovieDetails(
                        apiKey = BuildConfig.API_KEY,
                        language = Locale.getDefault().language,
                        id = id
                ))
    }

}