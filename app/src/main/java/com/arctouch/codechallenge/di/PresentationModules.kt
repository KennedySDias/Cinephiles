package com.arctouch.codechallenge.di

import com.arctouch.codechallenge.ui.moviedetails.MovieDetailsViewModel
import com.arctouch.codechallenge.ui.upcomingMovies.UpcomingMoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModules: Module = module {
    viewModel { UpcomingMoviesViewModel(get(), get(), get()) }
    viewModel { MovieDetailsViewModel(get(), get()) }
}
