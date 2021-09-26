package com.example.weatherappassignment.data.repository

import com.example.weatherappassignment.data.model.WeatherForecastDayObject
import io.reactivex.Completable
import io.reactivex.Single

interface WeatherRepository {
    fun getWeatherForecast(cityName: String): Single<List<WeatherForecastDayObject>>

    fun getStoredWeatherForecast(): Single<List<WeatherForecastDayObject>>

    fun saveWeatherForecastDay(weatherForecastDayObject: List<WeatherForecastDayObject>): Completable

    fun deleteWeatherForecastDay(): Completable
}