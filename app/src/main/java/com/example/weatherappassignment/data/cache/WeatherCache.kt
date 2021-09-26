package com.example.weatherappassignment.data.cache

import com.example.weatherappassignment.data.model.WeatherForecastDayObject
import io.reactivex.Completable
import io.reactivex.Single

interface WeatherCache {
    fun getWeatherForecast(): Single<List<WeatherForecastDayObject>>

    fun saveWeatherForecastDay(weatherForecastDayObject: List<WeatherForecastDayObject>): Completable

    fun deleteWeatherForecast(): Completable
}