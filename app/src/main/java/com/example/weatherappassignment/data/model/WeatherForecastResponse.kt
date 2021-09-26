package com.example.weatherappassignment.data.model

import com.google.gson.annotations.SerializedName

data class WeatherForecastResponse (
    @SerializedName("city") var city : City?,
    @SerializedName("cod") var cod : String?,
    @SerializedName("message") var message : Double?,
    @SerializedName("cnt") var cnt : Int?,
    @SerializedName("list") var dayDetailList : List<DayDetailItem?>?
)

data class City (
    @SerializedName("id") var id : Int?,
    @SerializedName("name") var name : String?,
    @SerializedName("country") var country : String?,
    @SerializedName("population") var population : Int?,
    @SerializedName("timezone") var timezone : Int?
)

data class DayDetailItem (
    @SerializedName("dt") var dt : Long?,
    @SerializedName("sunrise") var sunrise : Int?,
    @SerializedName("sunset") var sunset : Int?,
    @SerializedName("temp") var temp : Temp?,
    @SerializedName("feels_like") var feelsLike : FeelsLike?,
    @SerializedName("pressure") var pressure : Int?,
    @SerializedName("humidity") var humidity : Int?,
    @SerializedName("weather") var weather : List<Weather?>?,
    @SerializedName("speed") var speed : Double?,
    @SerializedName("deg") var deg : Int?,
    @SerializedName("gust") var gust : Double?,
    @SerializedName("clouds") var clouds : Int?,
    @SerializedName("pop") var pop : Double?,
    @SerializedName("rain") var rain : Double?
)

data class Temp (
    @SerializedName("day") var day : Double?,
    @SerializedName("min") var min : Double?,
    @SerializedName("max") var max : Double?,
    @SerializedName("night") var night : Double?,
    @SerializedName("eve") var eve : Double?,
    @SerializedName("morn") var morn : Double?
)

data class FeelsLike (
    @SerializedName("day") var day : Double?,
    @SerializedName("night") var night : Double?,
    @SerializedName("eve") var eve : Double?,
    @SerializedName("morn") var morn : Double?
)

data class Weather (
    @SerializedName("id") var id : Int?,
    @SerializedName("main") var main : String?,
    @SerializedName("description") var description : String?,
    @SerializedName("icon") var icon : String?
)