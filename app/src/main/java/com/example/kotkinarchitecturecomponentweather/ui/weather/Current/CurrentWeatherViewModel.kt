package com.example.kotkinarchitecturecomponentweather.ui.weather.Current

import androidx.lifecycle.ViewModel;
import com.example.kotkinarchitecturecomponentweather.Internal.UnitSystem
import com.example.kotkinarchitecturecomponentweather.Internal.lazyDeffered
import com.example.kotkinarchitecturecomponentweather.data.Repository.ForecastRepository
import com.example.kotkinarchitecturecomponentweather.data.providers.UnitProvider
import com.example.kotkinarchitecturecomponentweather.ui.base.WeatherViewModel

class CurrentWeatherViewModel(
    private  val forecastRepository:ForecastRepository,
    unitProvider: UnitProvider
) : WeatherViewModel(forecastRepository,unitProvider ) {

    val weather by lazyDeffered {
        forecastRepository.getCurrentWeather(isMetricUnit)
    }
}
