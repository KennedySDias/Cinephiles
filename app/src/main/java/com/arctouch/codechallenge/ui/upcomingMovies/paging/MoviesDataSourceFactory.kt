package com.arctouch.codechallenge.ui.upcomingMovies.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.domain.model.ShortMovieData
import com.example.domain.usecase.GetUpcomingMoviesUseCase

class MoviesDataSourceFactory(
        private val upcomingMoviesUseCase: GetUpcomingMoviesUseCase,
        private val onComplete: () -> Unit,
        private val onError: (Exception) -> Unit
) : DataSource.Factory<Int, ShortMovieData>() {

    val source = MutableLiveData<MoviesKeyedDataSource>()

    override fun create(): DataSource<Int, ShortMovieData> {
        val source = MoviesKeyedDataSource(
                upcomingMoviesUseCase,
                onComplete,
                onError)
        this.source.postValue(source)
        return source
    }
}
