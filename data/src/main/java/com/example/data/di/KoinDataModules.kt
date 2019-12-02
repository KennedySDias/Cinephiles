package com.example.data.di

import com.example.data.*
import com.example.data.local.GenresDataBase
import com.example.data.mapper.GenreMapper
import com.example.data.mapper.MovieMapper
import com.example.data.mapper.UpcomingMoviesMapper
import com.example.data.remote.RetrofitBuilder
import com.example.data.util.MovieImageUrlBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val cacheDataSourceModule = module {
    single { GenresDataBase.createDataBase(androidContext()) }
    factory<CacheDataSource> { CacheDataSourceImpl(get(), get()) }
}

val remoteDataSourceModule = module {
    single { RetrofitBuilder(androidContext().cacheDir) }
    factory<RemoteDataSource> { RemoteDataSourceImpl(get(), get(), get(), get()) }
}

val repositoryModule = module {
    factory<TMDBRepository> { TMDBRepositoryImpl(get(), get()) }
}

val mapperDataModule = module {
    single { GenreMapper() }
    single { MovieMapper(get()) }
    single { UpcomingMoviesMapper(get()) }
}

val utilsModule = module {
    single { MovieImageUrlBuilder() }
}