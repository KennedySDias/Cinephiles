package com.example.data.util

import com.example.data.BuildConfig

class MovieImageUrlBuilder {

    fun buildPosterUrl(posterPath: String): String {
        return BuildConfig.API_POSTER_URL + posterPath + "?api_key=" + BuildConfig.API_KEY
    }

    fun buildBackdropUrl(backdropPath: String): String {
        return BuildConfig.API_BACKDROP_URL + backdropPath + "?api_key=" + BuildConfig.API_KEY
    }
}
