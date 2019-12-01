package com.arctouch.codechallenge

import android.app.Application
import com.example.data.di.cacheDataSourceModule
import com.example.data.di.mapperDataModule
import com.example.data.di.remoteDataSourceModule
import com.example.data.di.repositoryModule
import com.example.domain.di.mapperDomainModule
import com.example.domain.di.useCasesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CinephilesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        configureKoin()
    }

    private fun configureKoin() {
        val appModules = listOf(
                // Data
                cacheDataSourceModule,
                remoteDataSourceModule,
                repositoryModule,
                mapperDataModule,

                // Domain
                mapperDomainModule,
                useCasesModule
        )

        startKoin {
            // Android context
            androidContext(this@CinephilesApplication)
            // modules
            modules(appModules)
        }
    }

}