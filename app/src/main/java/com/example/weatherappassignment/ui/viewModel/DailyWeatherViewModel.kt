package com.example.weatherappassignment.ui.viewModel

import com.example.weatherappassignment.data.model.WeatherForecastDayObject

class DailyWeatherViewModel(
    val dayDetailItem: WeatherForecastDayObject,
    private val onItemClickCallback: (WeatherForecastDayObject) -> Unit
) {

    fun onItemClick() {
        onItemClickCallback.invoke(dayDetailItem)
    }

    fun getId() = dayDetailItem.dateTime
}