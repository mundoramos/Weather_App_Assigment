package com.example.weatherappassignment.factory

import com.example.weatherappassignment.data.model.*
import java.util.*

object WeatherRemoteDataFactory {
    fun makeWeatherResponse(): WeatherForecastResponse {
        return WeatherForecastResponse(
            City(
                randomInt(),
                randomString(),
                randomString(),
                randomInt(),
                randomInt()
            ),
            randomString(),
            randomDouble(),
            randomInt(),
            listOf()
        )
    }

    fun makeWeatherDaily(): WeatherForecastDayObject {
        return WeatherForecastDayObject(
            randomInt().toLong(),
            randomInt(),
            randomString(),
            randomInt(),
            randomInt(),
            randomInt(),
            randomInt().toDouble(),
            randomInt().toDouble(),
            randomInt().toDouble(),
            randomString(),
        )
    }

    fun randomString(): String {
        return UUID.randomUUID().toString()
    }

    fun randomInt(): Int {
        return UUID.randomUUID().toString().length
    }

    fun randomDouble(): Double {
        return UUID.randomUUID().toString().length.toDouble()
    }
}