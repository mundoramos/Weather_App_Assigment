package com.example.weatherappassignment.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.weatherappassignment.R
import com.example.weatherappassignment.ui.holder.WeatherViewHolder
import com.example.weatherappassignment.ui.viewModel.DailyWeatherViewModel
import javax.inject.Inject

class WeatherListAdapter @Inject constructor() :
    ListAdapter<DailyWeatherViewModel, WeatherViewHolder>(WeatherListDiffCallback()),
    BindableAdapter<List<DailyWeatherViewModel>> {
    var dalyWeatherList = listOf<DailyWeatherViewModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_weather,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        return holder.bind(dalyWeatherList[position])
    }

    override fun getItemCount() = dalyWeatherList.size

    override fun setData(data: List<DailyWeatherViewModel>) {
        dalyWeatherList.apply {
            submitList(data)
            dalyWeatherList = data
        }
    }
}

class WeatherListDiffCallback : DiffUtil.ItemCallback<DailyWeatherViewModel>() {
    override fun areItemsTheSame(
        oldItem: DailyWeatherViewModel,
        newItem: DailyWeatherViewModel
    ): Boolean {
        return oldItem.getId() == newItem.getId()
    }

    override fun areContentsTheSame(
        oldItem: DailyWeatherViewModel,
        newItem: DailyWeatherViewModel
    ): Boolean {
        return oldItem.getId() == newItem.getId()
    }
}