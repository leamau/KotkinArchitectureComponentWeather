package com.example.kotkinarchitecturecomponentweather.ui.weather.Current

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotkinarchitecturecomponentweather.data.Repository.ForecastRepository
import com.example.kotkinarchitecturecomponentweather.data.providers.UnitProvider

class CurrentWeatherViewModelFactory(
    private val forecastRepository: ForecastRepository,
    private val unitProvider: UnitProvider
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CurrentWeatherViewModel(forecastRepository, unitProvider) as T
    }
}