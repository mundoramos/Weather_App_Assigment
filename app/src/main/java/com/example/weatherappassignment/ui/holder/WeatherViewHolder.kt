package com.example.weatherappassignment.ui.holder

import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.weatherappassignment.databinding.ItemWeatherBinding
import com.example.weatherappassignment.ui.viewModel.DailyWeatherViewModel

class WeatherViewHolder(
    val view: View
) : BaseHolder<DailyWeatherViewModel>(view) {
    override fun bind(item: DailyWeatherViewModel) {
        DataBindingUtil.bind<ItemWeatherBinding>(view)?.run {
            data = item
            weatherLayout.setOnClickListener {
                item.onItemClick()
            }
        }
    }
}