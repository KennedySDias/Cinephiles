package com.example.data

import com.example.data.di.mapperDataModule
import com.example.data.local.entity.GenreCache
import com.example.data.mapper.GenreMapper
import com.example.data.mapper.MovieMapper
import com.example.data.mapper.UpcomingMoviesMapper
import com.example.data.model.FullMovieModel
import com.example.data.model.GenreModel
import com.example.data.model.ShortMovieModel
import com.example.data.model.UpcomingMoviesModel
import com.example.data.remote.payload.GenreResponseModel
import com.example.data.remote.payload.MovieFullResponseModel
import com.example.data.remote.payload.MovieShortResponseModel
import com.example.data.remote.payload.UpcomingMoviesResponseModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject

class MapperUnitTest : AutoCloseKoinTest() {

    private val modules = listOf(
            mapperDataModule
    )

    private val genreMapper by inject<GenreMapper>()
    private val movieMapper by inject<MovieMapper>()
    private val upcomingMoviesMapper by inject<UpcomingMoviesMapper>()

    private val genreCacheEmpty = GenreCache(
            id = 0,
            name = ""
    )

    private val genreModelEmpty = GenreModel(
            id = 0,
            name = ""
    )

    private val genreResponseModelEmpty = GenreResponseModel(
            id = 0,
            name = ""
    )

    private val genreResponseModelNull = GenreResponseModel(
            id = null,
            name = null
    )

    private val shortMovieModelEmpty = ShortMovieModel(
            popularity = 0f,
            voteCount = 0,
            video = false,
            posterPath = "",
            id = 0,
            adult = false,
            backdropPath = "",
            originalLanguage = "",
            originalTitle = "",
            genreIds = emptyList(),
            title = "",
            voteAverage = 0f,
            overview = "",
            releaseDate = ""
    )

    private val fullMovieModelEmpty = FullMovieModel(
            adult = false,
            backdropPath = "",
            budget = 0,
            genres = emptyList(),
            homepage = "",
            id = 0,
            imdbId = "",
            originalLanguage = "",
            originalTitle = "",
            overview = "",
            popularity = 0,
            posterPath = "",
            releaseDate = "",
            revenue = 0,
            runtime = 0,
            status = "",
            tagline = "",
            title = "",
            video = false,
            voteAverage = 0f,
            voteCount = 0
    )

    private val movieShortResponseModelEmpty = MovieShortResponseModel(
            popularity = 0f,
            voteCount = 0,
            video = false,
            posterPath = "",
            id = 0,
            adult = false,
            backdropPath = "",
            originalLanguage = "",
            originalTitle = "",
            genreIds = emptyList(),
            title = "",
            voteAverage = 0f,
            overview = "",
            releaseDate = ""
    )

    private val movieFullResponseModelEmpty = MovieFullResponseModel(
            adult = false,
            backdropPath = "",
            budget = 0,
            genres = emptyList(),
            homepage = "",
            id = 0,
            imdbId = "",
            originalLanguage = "",
            originalTitle = "",
            overview = "",
            popularity = 0,
            posterPath = "",
            releaseDate = "",
            revenue = 0,
            runtime = 0,
            status = "",
            tagline = "",
            title = "",
            video = false,
            voteAverage = 0f,
            voteCount = 0
    )

    private val movieShortResponseModelNull = MovieShortResponseModel(
            popularity = null,
            voteCount = null,
            video = null,
            posterPath = null,
            id = null,
            adult = null,
            backdropPath = null,
            originalLanguage = null,
            originalTitle = null,
            genreIds = null,
            title = null,
            voteAverage = null,
            overview = null,
            releaseDate = null
    )

    private val movieFullResponseModelNull = MovieFullResponseModel(
            adult = null,
            backdropPath = null,
            budget = null,
            genres = null,
            homepage = null,
            id = null,
            imdbId = null,
            originalLanguage = null,
            originalTitle = null,
            overview = null,
            popularity = null,
            posterPath = null,
            releaseDate = null,
            revenue = null,
            runtime = null,
            status = null,
            tagline = null,
            title = null,
            video = null,
            voteAverage = null,
            voteCount = null
    )

    private val upcomingMoviesModelEmpty = UpcomingMoviesModel(
            movies = emptyList(),
            page = 0,
            totalPages = 0,
            totalResults = 0
    )

    private val upcomingMoviesResponseModelEmpty = UpcomingMoviesResponseModel(
            results = emptyList(),
            page = 0,
            totalPages = 0,
            totalResults = 0
    )

    private val upcomingMoviesResponseModelNull = UpcomingMoviesResponseModel(
            results = null,
            page = null,
            totalPages = null,
            totalResults = null
    )

    @Before
    fun before() {
        startKoin {
            modules(modules)
        }
    }

    @Test
    fun `GenreMapper Cache To Model Empty`() {
        val cache = genreCacheEmpty
        val model = genreModelEmpty

        val result = genreMapper.mapCacheToModel(cache)

        assertEquals(model, result)
    }

    @Test
    fun `GenreMapper Model To Cache Empty`() {
        val cache = genreCacheEmpty
        val model = genreModelEmpty

        val result = genreMapper.mapCacheToModel(cache)

        assertEquals(model, result)
    }

    @Test
    fun `GenreMapper Payload To Models Empty`() {
        val payload = genreResponseModelEmpty
        val model = genreModelEmpty

        val result = genreMapper.mapPayloadToModel(payload)

        assertEquals(model, result)
    }

    @Test
    fun `GenreMapper Payload To Models Null`() {
        val payload = null
        val model = genreModelEmpty

        val result = genreMapper.mapPayloadToModel(payload)

        assertEquals(model, result)
    }

    @Test
    fun `GenreMapper Payload To Models Null2`() {
        val payload = genreResponseModelNull
        val model = genreModelEmpty

        val result = genreMapper.mapPayloadToModel(payload)

        assertEquals(model, result)
    }

    @Test
    fun `MovieMapper Payload To Models Empty - Short`() {
        val payload = movieShortResponseModelEmpty
        val model = shortMovieModelEmpty

        val result = movieMapper.mapPayloadToModel(payload)

        assertEquals(model, result)
    }

    @Test
    fun `MovieMapper Payload To Models Empty - Full`() {
        val payload = movieFullResponseModelEmpty
        val model = fullMovieModelEmpty

        val result = movieMapper.mapPayloadToModel(payload)

        assertEquals(model, result)
    }

    @Test
    fun `MovieMapper Payload Null To Model - Short`() {
        val payload: MovieShortResponseModel? = null
        val model = shortMovieModelEmpty

        val result = movieMapper.mapPayloadToModel(payload)

        assertEquals(model, result)
    }

    @Test
    fun `MovieMapper Payload Null To Model - Full`() {
        val payload: MovieFullResponseModel? = null
        val model = fullMovieModelEmpty

        val result = movieMapper.mapPayloadToModel(payload)

        assertEquals(model, result)
    }

    @Test
    fun `MovieMapper Payload Null2 To Models - Short`() {
        val payload = movieShortResponseModelNull
        val model = shortMovieModelEmpty

        val result = movieMapper.mapPayloadToModel(payload)

        assertEquals(model, result)
    }

    @Test
    fun `MovieMapper Payload Null2 To Models - Full`() {
        val payload = movieFullResponseModelNull
        val model = fullMovieModelEmpty

        val result = movieMapper.mapPayloadToModel(payload)

        assertEquals(model, result)
    }

    @Test
    fun `UpcomingMoviesMapper Payload Empty To Models`() {
        val payload = upcomingMoviesResponseModelEmpty
        val model = upcomingMoviesModelEmpty

        val result = upcomingMoviesMapper.mapPayloadToModel(payload)

        assertEquals(model, result)
    }

    @Test
    fun `UpcomingMoviesMapper Payload Null To Models`() {
        val payload = null
        val model = upcomingMoviesModelEmpty

        val result = upcomingMoviesMapper.mapPayloadToModel(payload)

        assertEquals(model, result)
    }

    @Test
    fun `UpcomingMoviesMapper Payload Null2 To Models`() {
        val payload = upcomingMoviesResponseModelNull
        val model = upcomingMoviesModelEmpty

        val result = upcomingMoviesMapper.mapPayloadToModel(payload)

        assertEquals(model, result)
    }

}
