package com.example.weatherappassignment.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.weatherappassignment.R
import com.example.weatherappassignment.databinding.ActivityWeatherBinding
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class WeatherActivity : AppCompatActivity(), HasAndroidInjector {
    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        DataBindingUtil.setContentView<ActivityWeatherBinding>(this, R.layout.activity_weather)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return fragmentInjector
    }
}