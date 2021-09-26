package com.example.weatherappassignment.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weatherappassignment.R
import com.example.weatherappassignment.util.notNullDouble
import com.example.weatherappassignment.util.notNullInt
import com.example.weatherappassignment.util.notNullLong
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "weather_forecast")
data class WeatherForecastDayObject(
    @PrimaryKey
    val dateTime: Long,
    val cityId: Int,
    val cityName: String,
    val tempMax: Int,
    val tempMin: Int,
    val humidity: Int,
    var speed: Double,
    var rain: Double,
    var pop: Double,
    var weatherMainTitle: String
) {
    var isWarm = tempMax > 25 && weatherMainTitle == "Clear"

    fun getWeatherHotOrCold(): String {
        return when {
            tempMax > 25 -> "Hot"
            tempMax < 10 -> "Cold"
            else -> ""
        }
    }

    fun getDayName(): String {
        val date = Date(dateTime * 1000L)
        val timeStampFormat = SimpleDateFormat("EEE", Locale.getDefault())
        return timeStampFormat.format(date)
    }

    fun getWeatherStatus(): String = weatherMainTitle

    fun getMaxWeatherTemperature(): String = tempMax.toString()

    fun getMinWeatherTemperature(): String = tempMin.toString()

    fun getWeatherStatusImage(): Int {
        return when (weatherMainTitle) {
            "Rain" -> R.drawable.ic_weather_rain
            "Clouds" -> R.drawable.ic_weather_cloud
            else -> R.drawable.ic_weather_clean
        }
    }

    fun getDate(): String {
        val dt = dateTime.notNullLong()
        val date = Date(dt * 1000L)
        val timeStampFormat = SimpleDateFormat("EEEE, dd MMM", Locale.getDefault())
        return timeStampFormat.format(date)
    }

    fun getWeatherSpeed(): Float = speed.notNullDouble().toFloat()

    fun getWeatherHumidity(): Int = humidity.notNullInt()

    fun getRainProbability(): Int = (pop.notNullDouble() * 100).toInt()
}