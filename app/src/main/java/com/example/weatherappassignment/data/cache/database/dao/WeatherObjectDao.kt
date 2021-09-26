package com.example.weatherappassignment.data.cache.database.dao

import androidx.room.*
import com.example.weatherappassignment.data.model.WeatherForecastDayObject
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface WeatherObjectDao {
    @Query("SELECT * FROM weather_forecast")
    fun getWeatherForecast(): Single<List<WeatherForecastDayObject>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveWeatherForecastDay(weatherForecastDayObject: WeatherForecastDayObject): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertAll(list: List<WeatherForecastDayObject>): Completable

    @Query("DELETE FROM weather_forecast")
    fun deleteAllWeatherForecast(): Completable
}