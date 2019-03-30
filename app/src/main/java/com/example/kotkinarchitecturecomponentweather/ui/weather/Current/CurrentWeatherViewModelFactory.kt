package com.example.kotkinarchitecturecomponentweather.ui.weather.Current

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotkinarchitecturecomponentweather.data.Repository.ForecastRepository

class CurrentWeatherViewModelFactory(
    private val forecastRepository: ForecastRepository
) :ViewModelProvider.NewInstanceFactory(){
    override fun <T:ViewModel?> create(modelClass: Class<T>):T{
        return CurrentWeatherViewModel(forecastRepository) as T
    }
}