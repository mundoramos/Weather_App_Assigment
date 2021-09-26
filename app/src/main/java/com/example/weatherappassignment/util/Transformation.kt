package com.example.weatherappassignment.util

import com.example.weatherappassignment.data.model.*
import io.reactivex.Single

fun Single<WeatherForecastResponse>.toWeatherDailyList(): Single<List<WeatherForecastDayObject>> {
    return this.map { response ->
        response.dayDetailList?.map {
            WeatherForecastDayObject(
                dateTime = it?.dt.notNullLong(),
                cityId = response.city?.id.notNullInt(),
                cityName = response.city?.name.orEmpty(),
                tempMax = it?.temp?.max.notNullDouble().toInt(),
                tempMin = it?.temp?.min.notNullDouble().toInt(),
                humidity = it?.humidity.notNullInt(),
                rain = it?.rain.notNullDouble(),
                speed = it?.speed.notNullDouble(),
                pop = it?.pop.notNullDouble(),
                weatherMainTitle = it?.weather?.firstOrNull()?.main.orEmpty()
            )
        }
    }
}