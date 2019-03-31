package com.example.kotkinarchitecturecomponentweather.ui.weather.List

import androidx.lifecycle.ViewModel;
import com.example.kotkinarchitecturecomponentweather.Internal.lazyDeffered
import com.example.kotkinarchitecturecomponentweather.data.Repository.ForecastRepository
import com.example.kotkinarchitecturecomponentweather.data.providers.UnitProvider
import com.example.kotkinarchitecturecomponentweather.ui.base.WeatherViewModel
import org.threeten.bp.LocalDate

class ListWeatherViewModel(
    private val forecastRepository:ForecastRepository,
    unitProvider: UnitProvider
) : WeatherViewModel(forecastRepository,unitProvider) {
    val weatherEntries by lazyDeffered {
        forecastRepository.getFutureWeatherList(LocalDate.now(),isMetricUnit)
    }
}
