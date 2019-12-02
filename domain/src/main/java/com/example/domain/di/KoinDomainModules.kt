package com.example.domain.di

import com.example.domain.mapper.GenreMapper
import com.example.domain.mapper.MovieMapper
import com.example.domain.mapper.UpcomingMoviesMapper
import com.example.domain.usecase.GetGenresMoviesUseCase
import com.example.domain.usecase.GetMovieDetailsUseCase
import com.example.domain.usecase.GetUpcomingMoviesUseCase
import org.koin.dsl.module

val mapperDomainModule = module {
    single { GenreMapper() }
    single { MovieMapper(get()) }
    single { UpcomingMoviesMapper(get()) }
}

val useCasesModule = module {
    factory { GetGenresMoviesUseCase(get(), get()) }
    factory { GetUpcomingMoviesUseCase(get(), get()) }
    factory { GetMovieDetailsUseCase(get(), get()) }
}
