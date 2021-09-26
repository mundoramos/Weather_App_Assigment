package com.example.weatherappassignment.data.repository

import com.example.weatherappassignment.data.cache.WeatherCache
import com.example.weatherappassignment.data.model.WeatherForecastDayObject
import com.example.weatherappassignment.data.remote.WeatherRemote
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class WeatherRepositoryImp @Inject constructor(
    private val weatherRemote: WeatherRemote,
    private val weatherCache: WeatherCache
) : WeatherRepository {
    override fun getWeatherForecast(cityName: String): Single<List<WeatherForecastDayObject>> {
        return Single.zip(
            weatherRemote.getWeatherForecast(cityName),
            Single.just(0),
            BiFunction<List<WeatherForecastDayObject>, Int, List<WeatherForecastDayObject>> { remoteWeather, _ ->
                remoteWeather
            }
        )
    }

    override fun getStoredWeatherForecast(): Single<List<WeatherForecastDayObject>> {
        return Single.zip(
            weatherCache.getWeatherForecast(),
            Single.just(0),
            BiFunction<List<WeatherForecastDayObject>, Int, List<WeatherForecastDayObject>> { cacheWeather, _ ->
                cacheWeather
            }
        )
    }

    override fun saveWeatherForecastDay(weatherForecastDayObject: List<WeatherForecastDayObject>): Completable {
        return weatherCache.saveWeatherForecastDay(weatherForecastDayObject)
    }

    override fun deleteWeatherForecastDay(): Completable {
        return weatherCache.deleteWeatherForecast()
    }
}