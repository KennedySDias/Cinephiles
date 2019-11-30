package com.example.data.remote.api

import com.example.data.remote.payload.GenresResponseModel
import com.example.data.remote.payload.MovieFullResponseModel
import com.example.data.remote.payload.UpcomingMoviesResponseModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBApi {

    @GET("genre/movie/list")
    suspend fun fetchGenresMovies(
            @Query("api_key") apiKey: String,
            @Query("language") language: String
    ): GenresResponseModel

    @GET("movie/upcoming")
    suspend fun fetchUpcomingMovies(
            @Query("api_key") apiKey: String,
            @Query("language") language: String,
            @Query("page") page: Long,
            @Query("region") region: String
    ): UpcomingMoviesResponseModel

    @GET("movie/{id}")
    suspend fun fetchMovieDetails(
            @Path("id") id: Long,
            @Query("api_key") apiKey: String,
            @Query("language") language: String
    ): MovieFullResponseModel

}