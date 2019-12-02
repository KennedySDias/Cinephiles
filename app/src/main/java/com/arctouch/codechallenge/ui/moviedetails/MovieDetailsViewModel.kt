package com.arctouch.codechallenge.ui.moviedetails

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.arctouch.codechallenge.custom.SingleLiveEvent
import com.example.data.util.MovieImageUrlBuilder
import com.example.domain.expection.NotConnectedException
import com.example.domain.expection.UnauthorizedException
import com.example.domain.model.FullMovieData
import com.example.domain.model.ShortMovieData
import com.example.domain.usecase.GetMovieDetailsUseCase
import java.util.concurrent.TimeoutException

class MovieDetailsViewModel(
        private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
        private val movieImageUrlBuilder: MovieImageUrlBuilder
) : ViewModel() {

    lateinit var shortMovie: ShortMovieData
    val fullMovieOb = SingleLiveEvent<FullMovieData>()

    val errorOb = SingleLiveEvent<String>()
    val fatalErrorOb = SingleLiveEvent<String>()
    val notConnectedOb = SingleLiveEvent<Boolean>()
    val gettingDataOb = SingleLiveEvent<Boolean>()

    fun init(arguments: Bundle?) {
        val model = arguments?.getParcelable<ShortMovieData>(MovieDetailsFragment.EXTRA_MOVIE)
        if (model != null) {
            this.shortMovie = model
            getFullMovie()
        } else {
            fatalErrorOb.value = "Fatal error"
        }
    }

    fun getFullMovie() {
        notConnectedOb.value = false
        gettingDataOb.value = true

        getMovieDetailsUseCase.id = shortMovie.id
        getMovieDetailsUseCase.execute {

            onComplete {
                gettingDataOb.value = false
                fullMovieOb.value = it
            }

            onError { error ->
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

            onCancel { throwable ->
                gettingDataOb.value = false
            }

        }
    }

    fun getPosterPath(poster: String): String {
        return movieImageUrlBuilder.buildPosterUrl(poster)
    }

    fun getBackdropPath(backdrop: String): String {
        return movieImageUrlBuilder.buildBackdropUrl(backdrop)
    }

    fun getGenresNames(fullMovieData: FullMovieData): String {
        return fullMovieData.genres.joinToString { it.name }
    }

}