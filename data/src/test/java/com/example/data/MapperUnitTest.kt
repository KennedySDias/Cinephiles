package com.example.data

import com.example.data.di.mapperDataModule
import com.example.data.local.entity.GenreCache
import com.example.data.mapper.GenreMapper
import com.example.data.model.GenreModel
import com.example.data.remote.payload.GenreResponseModel
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


}
