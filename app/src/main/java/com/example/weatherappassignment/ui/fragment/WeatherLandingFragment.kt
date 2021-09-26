package com.example.weatherappassignment.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherappassignment.R
import com.example.weatherappassignment.databinding.FragmentWeatherLandingBinding
import com.example.weatherappassignment.ui.adapter.WeatherListAdapter
import com.example.weatherappassignment.ui.injection.ViewModelFactory
import com.example.weatherappassignment.ui.viewModel.WeatherViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class WeatherLandingFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val navController: NavController by lazy { findNavController() }

    private val weatherViewModel: WeatherViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get(WeatherViewModel::class.java)
    }

    private var weatherListAdapter: WeatherListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        weatherViewModel.run {
            getWeatherForecast()

            viewSelectedWeatherDay.observe(this@WeatherLandingFragment, Observer {
                navController.navigate(R.id.action_weatherLandingFragment_to_weatherDayDetailFragment)
            })
        }

        weatherListAdapter = WeatherListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<FragmentWeatherLandingBinding>(
            inflater,
            R.layout.fragment_weather_landing,
            container,
            false
        )?.run {
            lifecycleOwner = this@WeatherLandingFragment
            vm = weatherViewModel

            (activity as AppCompatActivity?)?.setSupportActionBar(weatherToolbar)

            val appBarLayoutParams = weatherAppBar.layoutParams
            appBarLayoutParams.height = (resources.displayMetrics.heightPixels * 0.80).toInt()

            forecastRecyclerview.layoutManager = LinearLayoutManager(activity)
            forecastRecyclerview.adapter = weatherListAdapter

            root
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }
}