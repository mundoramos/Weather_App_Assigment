package com.example.weatherappassignment.data.remote

import com.example.weatherappassignment.data.model.WeatherForecastDayObject
import io.reactivex.Single

interface WeatherRemote {
    fun getWeatherForecast(cityName: String): Single<List<WeatherForecastDayObject>>
}