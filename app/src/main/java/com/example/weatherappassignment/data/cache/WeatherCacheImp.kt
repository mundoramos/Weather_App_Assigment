package com.example.weatherappassignment.data.cache

import com.example.weatherappassignment.data.cache.database.WeatherDatabase
import com.example.weatherappassignment.data.model.WeatherForecastDayObject
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class WeatherCacheImp @Inject constructor(
    val database: WeatherDatabase
) : WeatherCache {
    override fun getWeatherForecast(): Single<List<WeatherForecastDayObject>> {
        return database.weatherDao().getWeatherForecast()
    }

    override fun saveWeatherForecastDay(weatherForecastDayObject: List<WeatherForecastDayObject>): Completable {
        return database.weatherDao().insertAll(weatherForecastDayObject)
    }

    override fun deleteWeatherForecast(): Completable {
        return database.weatherDao().deleteAllWeatherForecast()
    }
}