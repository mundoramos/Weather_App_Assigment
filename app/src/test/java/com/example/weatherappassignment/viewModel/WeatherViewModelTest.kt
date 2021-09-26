package com.example.weatherappassignment.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.weatherappassignment.data.model.WeatherForecastDayObject
import com.example.weatherappassignment.data.repository.WeatherRepository
import com.example.weatherappassignment.ui.viewModel.WeatherViewModel
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Completable
import io.reactivex.Single
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock

class WeatherViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private val weatherRepository: WeatherRepository = mock {
        on { getWeatherForecast(any()) } doAnswer { Single.just(weatherResponse) }
        on { deleteWeatherForecastDay() } doAnswer { Completable.complete() }
    }

    private val viewModel by lazy {
        WeatherViewModel(
            weatherRepository
        )
    }

    var weatherResponse : List<WeatherForecastDayObject> = listOf()

    @Test
    fun `Get Weather Forecast Response`() {
        viewModel.getWeatherForecast()
        assertNotNull(viewModel.weatherData.value)
    }
}
