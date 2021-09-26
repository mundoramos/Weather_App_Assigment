package com.example.weatherappassignment.ui.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.weatherappassignment.data.model.WeatherForecastDayObject
import com.example.weatherappassignment.data.repository.WeatherRepository
import com.example.weatherappassignment.ui.state.Resource
import com.example.weatherappassignment.ui.viewModel.base.BaseViewModel
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : BaseViewModel() {
    val weatherData = MutableLiveData<Resource<WeatherForecastDayObject>>()
    val weatherForecastList = MutableLiveData<Resource<List<DailyWeatherViewModel>>>()

    val viewSelectedWeatherDay = MutableLiveData<Boolean>()
    val selectedWeatherDay = MutableLiveData<Resource<WeatherForecastDayObject>>()

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    fun getWeatherForecast() {
        weatherData.postValue(Resource.loading())
        weatherForecastList.postValue(Resource.loading())

        disposables.add(
            weatherRepository.getWeatherForecast(cityName = "Paris")
                .subscribeOn(Schedulers.io())
                .doOnError {
                    getCacheWeatherForecast()
                }
                .subscribeWith(GetWeatherForecastSubscriber())
        )
    }

    private fun getCacheWeatherForecast() {
        disposables.add(
            weatherRepository.getStoredWeatherForecast()
                .subscribeOn(Schedulers.io())
                .subscribeWith(GetWeatherForecastCacheSubscriber())
        )
    }

    fun onItemClicked(dailyDetailItem: WeatherForecastDayObject) {
        viewSelectedWeatherDay.postValue(true)
        selectedWeatherDay.postValue(Resource.success(dailyDetailItem))
    }

    fun deleteToSaveNewWeatherForecastDays(weatherForecastList: List<WeatherForecastDayObject>) {
        disposables.add(
            weatherRepository.deleteWeatherForecastDay()
                .subscribeOn(Schedulers.io())
                .doAfterTerminate {
                    saveWeatherForecastDays(weatherForecastList)
                }
                .subscribeWith(DeleteWeatherForecastSubscriber())
        )
    }

    private fun saveWeatherForecastDays(weatherForecastList: List<WeatherForecastDayObject>) {
        disposables.add(
            weatherRepository.saveWeatherForecastDay(weatherForecastList)
                .subscribeOn(Schedulers.io())
                .subscribeWith(SaveWeatherForecastSubscriber())
        )
    }

    inner class GetWeatherForecastSubscriber :
        DisposableSingleObserver<List<WeatherForecastDayObject>>() {
        override fun onSuccess(t: List<WeatherForecastDayObject>) {
            deleteToSaveNewWeatherForecastDays(t)
            t.firstOrNull()?.let {
                weatherData.postValue(Resource.success(it))
            }
            weatherForecastList.postValue(
                Resource.success(t.drop(1).map { DailyWeatherViewModel(it, ::onItemClicked) })
            )
        }

        override fun onError(e: Throwable) {
            weatherData.postValue(Resource.error(e))
        }
    }

    inner class GetWeatherForecastCacheSubscriber :
        DisposableSingleObserver<List<WeatherForecastDayObject>>() {
        override fun onSuccess(t: List<WeatherForecastDayObject>) {
            t.firstOrNull()?.let {
                weatherData.postValue(Resource.success(it))
            }
            weatherForecastList.postValue(
                Resource.success(t.drop(1).map { DailyWeatherViewModel(it, ::onItemClicked) })
            )
        }

        override fun onError(e: Throwable) {
            weatherData.postValue(Resource.error(e))
        }
    }

    inner class DeleteWeatherForecastSubscriber : DisposableCompletableObserver() {
        override fun onComplete() {

        }

        override fun onError(e: Throwable) {

        }
    }

    inner class SaveWeatherForecastSubscriber : DisposableCompletableObserver() {
        override fun onComplete() {

        }

        override fun onError(e: Throwable) {

        }
    }
}