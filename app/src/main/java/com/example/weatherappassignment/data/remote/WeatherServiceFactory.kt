package com.example.weatherappassignment.data.remote

import com.example.weatherappassignment.data.remote.interceptor.NetworkConnectionInterceptor
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object WeatherServiceFactory {
    fun makeWeatherService(
        isDebug: Boolean,
        networkConnectionInterceptor: NetworkConnectionInterceptor
    ): WeatherService {
        val okHttpClient = makeOkHttpClient(networkConnectionInterceptor)
        return makeWeatherService(okHttpClient, Gson())
    }

    private fun makeWeatherService(okHttpClient: OkHttpClient, gson: Gson): WeatherService {
        val retrofit = makeService(
            okHttpClient,
            gson,
            "https://api.openweathermap.org"
        )
        return retrofit.create(WeatherService::class.java)
    }

    fun makeService(okHttpClient: OkHttpClient, gson: Gson, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun makeOkHttpClient(
        networkConnectionInterceptor: NetworkConnectionInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(networkConnectionInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .retryOnConnectionFailure(false)
            .build()
    }
}