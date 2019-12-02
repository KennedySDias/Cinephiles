package com.arctouch.codechallenge.ui.upcomingMovies.paging

import androidx.paging.PageKeyedDataSource
import com.example.domain.model.ShortMovieData
import com.example.domain.usecase.GetUpcomingMoviesUseCase

class MoviesKeyedDataSource(
        private val upcomingMoviesUseCase: GetUpcomingMoviesUseCase,
        private val onComplete: () -> Unit,
        private val onError: (Exception) -> Unit
) : PageKeyedDataSource<Int, ShortMovieData>() {

    override fun loadBefore(params: LoadParams<Int>,
                            callback: LoadCallback<Int, ShortMovieData>) {

        upcomingMoviesUseCase.page = 1
        upcomingMoviesUseCase.execute {

            onComplete { response ->
                callback.onResult(response.movies, 2)
                onComplete()
            }

            onError {
                onError(it)
            }

        }
    }

    override fun loadInitial(params: LoadInitialParams<Int>,
                             callback: LoadInitialCallback<Int, ShortMovieData>) {
        val currentPage = 1
        val nextPage = currentPage + 1

        makeLoadInitialRequest(params, callback, currentPage, nextPage)
    }

    private fun makeLoadInitialRequest(params: LoadInitialParams<Int>,
                                       callback: LoadInitialCallback<Int, ShortMovieData>,
                                       currentPage: Int,
                                       nextPage: Int) {

        upcomingMoviesUseCase.page = currentPage
        upcomingMoviesUseCase.execute {

            onComplete { response ->
                callback.onResult(response.movies, null, nextPage)
                onComplete()
            }


            onError {
                onError(it)
            }

        }
    }

    override fun loadAfter(params: LoadParams<Int>,
                           callback: LoadCallback<Int, ShortMovieData>) {

        val currentPage = params.key
        val nextPage = currentPage + 1

        makeLoadAfterRequest(params, callback, currentPage, nextPage)
    }

    private fun makeLoadAfterRequest(params: LoadParams<Int>,
                                     callback: LoadCallback<Int, ShortMovieData>,
                                     currentPage: Int,
                                     nextPage: Int) {

        upcomingMoviesUseCase.page = nextPage
        upcomingMoviesUseCase.execute {

            onComplete { response ->
                callback.onResult(response.movies, nextPage)
                onComplete()
            }

            onError {
                onError(it)
            }

        }
    }

}