package com.example.kotkinarchitecturecomponentweather.ui.weather.List

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotkinarchitecturecomponentweather.data.Repository.ForecastRepository
import com.example.kotkinarchitecturecomponentweather.data.providers.UnitProvider
import com.example.kotkinarchitecturecomponentweather.ui.base.WeatherViewModel

class FutureListWeatherViewModelFactory(
    private val forecastRepository: ForecastRepository,
    private val unitProvider: UnitProvider
) :ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T:ViewModel?> create(modelClass:Class<T>):T{
        return ListWeatherViewModel(
            forecastRepository,
            unitProvider
        ) as T
    }

}