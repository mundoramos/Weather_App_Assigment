package com.example.weatherappassignment.data.remote

import com.example.weatherappassignment.data.model.WeatherForecastDayObject
import com.example.weatherappassignment.util.toWeatherDailyList
import io.reactivex.Single
import javax.inject.Inject

class WeatherRemoteImp @Inject constructor(
    private val weatherService: WeatherService
) : WeatherRemote {

    override fun getWeatherForecast(cityName: String): Single<List<WeatherForecastDayObject>> {
        return weatherService.getWeatherForecast(cityName).toWeatherDailyList()
    }
}