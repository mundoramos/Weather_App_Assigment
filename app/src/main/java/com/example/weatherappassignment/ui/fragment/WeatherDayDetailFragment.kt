package com.example.weatherappassignment.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.weatherappassignment.R
import com.example.weatherappassignment.databinding.FragmentWeatherDayDetailBinding
import com.example.weatherappassignment.ui.injection.ViewModelFactory
import com.example.weatherappassignment.ui.viewModel.WeatherViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class WeatherDayDetailFragment : Fragment(), WeatherDetailHandler {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val navController: NavController by lazy { findNavController() }

    private val weatherViewModel: WeatherViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get(WeatherViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<FragmentWeatherDayDetailBinding>(
            inflater,
            R.layout.fragment_weather_day_detail,
            container,
            false
        )?.run {
            lifecycleOwner = this@WeatherDayDetailFragment
            vm = weatherViewModel
            handler = this@WeatherDayDetailFragment

            root
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onBackButton() {
        navController.popBackStack()
    }
}

interface WeatherDetailHandler {
    fun onBackButton()
}