package com.example.data

import com.example.data.local.dao.GenresDao
import com.example.data.mapper.GenreMapper
import com.example.data.model.GenreModel

interface CacheDataSource {

    suspend fun getAll(): List<GenreModel>

    suspend fun insert(item: GenreModel)

    suspend fun insertAll(list: List<GenreModel>)

    suspend fun update(list: List<GenreModel>)

    suspend fun delete(item: GenreModel)

    suspend fun deleteAll()

}

class CacheDataSourceImpl(
        private val genresDao: GenresDao,
        private val genreMapper: GenreMapper
) : CacheDataSource {

    override suspend fun getAll(): List<GenreModel> {
        return genresDao.getAll().map { genreMapper.mapCacheToModel(it) }
    }

    override suspend fun insert(item: GenreModel) {
        genresDao.insert(genreMapper.mapModelToCache(item))
    }

    override suspend fun insertAll(list: List<GenreModel>) {
        genresDao.insertAll(list.map { genreMapper.mapModelToCache(it) })
    }

    override suspend fun update(list: List<GenreModel>) {
        genresDao.update(list.map { genreMapper.mapModelToCache(it) })
    }

    override suspend fun delete(item: GenreModel) {
        genresDao.delete(genreMapper.mapModelToCache(item))
    }

    override suspend fun deleteAll() {
        genresDao.deleteAll()
    }

}