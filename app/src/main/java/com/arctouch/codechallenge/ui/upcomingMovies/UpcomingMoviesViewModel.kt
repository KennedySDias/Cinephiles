package com.arctouch.codechallenge.ui.upcomingMovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.arctouch.codechallenge.custom.SingleLiveEvent
import com.arctouch.codechallenge.ui.upcomingMovies.paging.MoviesDataSourceFactory
import com.example.data.util.MovieImageUrlBuilder
import com.example.domain.expection.NotConnectedException
import com.example.domain.expection.UnauthorizedException
import com.example.domain.model.GenreData
import com.example.domain.model.ShortMovieData
import com.example.domain.usecase.GetGenresMoviesUseCase
import com.example.domain.usecase.GetUpcomingMoviesUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import java.util.concurrent.TimeoutException
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class UpcomingMoviesViewModel(
        private val getGenresMoviesUseCase: GetGenresMoviesUseCase,
        private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
        private val movieImageUrlBuilder: MovieImageUrlBuilder
) : ViewModel() {

    val genresOb = SingleLiveEvent<List<GenreData>>()
    val moviesOb = SingleLiveEvent<LiveData<PagedList<ShortMovieData>>>()

    val errorOb = SingleLiveEvent<String>()
    val fatalErrorOb = SingleLiveEvent<String>()
    val notConnectedOb = SingleLiveEvent<Boolean>()
    val gettingDataOb = SingleLiveEvent<Boolean>()

    fun init() {
        getUpcomingMovies(false)
    }

    fun getUpcomingMovies(forceUpdate: Boolean) {
        CoroutineScope(Dispatchers.Main).async {
            notConnectedOb.value = false
            gettingDataOb.value = true

            var exception: Exception? = null
            genresOb.value = suspendCoroutine<List<GenreData>> { continuation ->
                getGenresMoviesUseCase.forceUpdate = forceUpdate
                getGenresMoviesUseCase.execute {

                    onComplete {
                        exception = null
                        continuation.resume(it)
                    }

                    onError { error ->
                        continuation.resume(emptyList())
                        exception = error
                    }

                }
            }

            if (genresOb.value?.isEmpty() != true) {
                val factory = MoviesDataSourceFactory(
                        upcomingMoviesUseCase = getUpcomingMoviesUseCase,
                        onComplete = {
                            gettingDataOb.value = false

                        },
                        onError = { error ->
                            gettingDataOb.value = false
                            when (error) {
                                is UnauthorizedException -> {
                                    fatalErrorOb.value = error.message
                                }
                                is TimeoutException -> {
                                    errorOb.value = error.message
                                }
                                is NotConnectedException -> {
                                    notConnectedOb.value = true
                                }
                                else -> {
                                    errorOb.value = error.message
                                }
                            }
                        }
                )
                val config = PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(10 * 2)
                        .setPageSize(10)
                        .build()
                moviesOb.value = LivePagedListBuilder(factory, config).build()
            } else {
                gettingDataOb.value = false
                errorOb.value = exception?.message
            }
        }
    }

    fun getPosterPath(poster: String) = movieImageUrlBuilder.buildPosterUrl(poster)

    fun getGenresNames(genreIds: MutableList<Long>): String {
        val result = genresOb.value?.filter { genreIds.contains(it.id) } ?: emptyList()
        return result.joinToString { it.name }
    }

}