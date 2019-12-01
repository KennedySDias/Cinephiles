package com.example.data.mapper

import com.example.data.local.entity.GenreCache
import com.example.data.model.GenreModel
import com.example.data.remote.payload.GenreResponseModel

class GenreMapper {

    fun mapCacheToModel(cache: GenreCache) = map(cache)

    private fun map(cache: GenreCache) = GenreModel(
            id = cache.id ?: 0,
            name = cache.name
    )

    fun mapModelToCache(model: GenreModel) = map(model)

    private fun map(model: GenreModel) = GenreCache(
            id = model.id,
            name = model.name
    )

    fun mapPayloadToModel(payload: GenreResponseModel?) = map(payload)

    private fun map(payload: GenreResponseModel?) = GenreModel(
            id = payload?.id ?: 0,
            name = payload?.name ?: ""
    )

}