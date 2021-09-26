package com.example.weatherappassignment.remote

import com.example.weatherappassignment.data.remote.WeatherRemoteImp
import com.example.weatherappassignment.data.remote.WeatherService
import com.example.weatherappassignment.factory.WeatherRemoteDataFactory
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@RunWith(JUnit4::class)
class WeatherRemoteImpTest {
    val service = mock(WeatherService::class.java)
    val remoteImp = WeatherRemoteImp(service)

    @Test
    fun `Get Weather calls server is success`() {
        val response = Single.just(WeatherRemoteDataFactory.makeWeatherResponse())
        whenever(service.getWeatherForecast(q = "Paris")).thenReturn(response)
        val testObserver = remoteImp.getWeatherForecast(cityName = "Paris").test()
        verify(service).getWeatherForecast(q = "Paris")
        testObserver.assertComplete()
    }

    //TODO(TEST FOR OTHER CLASSES)
}