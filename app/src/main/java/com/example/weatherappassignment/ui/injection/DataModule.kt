package com.example.weatherappassignment.ui.injection

import android.app.Application
import com.example.weatherappassignment.BuildConfig
import com.example.weatherappassignment.data.cache.WeatherCache
import com.example.weatherappassignment.data.cache.WeatherCacheImp
import com.example.weatherappassignment.data.cache.database.WeatherDatabase
import com.example.weatherappassignment.data.remote.*
import com.example.weatherappassignment.data.remote.interceptor.NetworkConnectionInterceptor
import com.example.weatherappassignment.data.repository.WeatherRepository
import com.example.weatherappassignment.data.repository.WeatherRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class DataModule {
    companion object {
        // Provides the service to be injected with custom parameters
        @Provides
        fun provideWeatherService(networkConnectionInterceptor: NetworkConnectionInterceptor): WeatherService {
            return WeatherServiceFactory.makeWeatherService(
                BuildConfig.DEBUG,
                networkConnectionInterceptor
            )
        }

        // Injects the database
        @Provides
        @Singleton
        fun provideWeatherDatabase(application: Application): WeatherDatabase {
            return WeatherDatabase.getDatabaseInstance(application)
        }
    }

    // Provides the class to be injected
    @Binds
    abstract fun bindWeatherRepository(weatherRepository: WeatherRepositoryImp): WeatherRepository

    @Binds
    abstract fun bindWeatherRemote(weatherRemote: WeatherRemoteImp): WeatherRemote

    @Binds
    abstract fun bindWeatherCache(weatherCache: WeatherCacheImp): WeatherCache
}