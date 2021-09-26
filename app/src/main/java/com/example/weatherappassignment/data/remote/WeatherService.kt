package com.example.weatherappassignment.data.remote

import com.example.weatherappassignment.data.model.WeatherForecastResponse
import com.example.weatherappassignment.util.QUERY_DAYS_CNT
import com.example.weatherappassignment.util.QUERY_MODE
import com.example.weatherappassignment.util.QUERY_UNITS
import com.example.weatherappassignment.util.WEATHER_API_KEY
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("/data/2.5/forecast/daily")
    fun getWeatherForecast(
        @Query("q") q: String,
        @Query("mode") mode: String = QUERY_MODE,
        @Query("units") units: String = QUERY_UNITS,
        @Query("cnt") cnt: Int = QUERY_DAYS_CNT,
        @Query("appId") appId: String = WEATHER_API_KEY
    ): Single<WeatherForecastResponse>
}