package com.arctouch.codechallenge

import android.app.Application
import com.arctouch.codechallenge.di.viewModelModules
import com.example.data.di.*
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
                utilsModule,

                // Domain
                mapperDomainModule,
                useCasesModule,

                // Presentation
                viewModelModules
        )

        startKoin {
            // Android context
            androidContext(this@CinephilesApplication)
            // modules
            modules(appModules)
        }
    }

}