package com.example.weatherappassignment.ui.injection

import android.app.Application
import com.example.weatherappassignment.ui.WeatherApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, FragmentModule::class, ViewModelModule::class, DataModule::class])
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: WeatherApplication)
}