package com.example.weatherappassignment.ui.injection

import com.example.weatherappassignment.ui.activity.WeatherActivity
import com.example.weatherappassignment.ui.fragment.WeatherDayDetailFragment
import com.example.weatherappassignment.ui.fragment.WeatherLandingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun weatherActivity(): WeatherActivity

    @ContributesAndroidInjector
    abstract fun weatherLanding(): WeatherLandingFragment

    @ContributesAndroidInjector
    abstract fun weatherDayDetailLanding(): WeatherDayDetailFragment
}
