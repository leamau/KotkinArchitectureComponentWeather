package com.example.kotkinarchitecturecomponentweather.ui.weather.Detail
import com.example.kotkinarchitecturecomponentweather.data.Repository.ForecastRepository
import com.example.kotkinarchitecturecomponentweather.data.providers.UnitProvider
import com.example.kotkinarchitecturecomponentweather.ui.base.WeatherViewModel
import org.threeten.bp.LocalDate
import com.example.kotkinarchitecturecomponentweather.Internal.lazyDeffered

class DetailWeatherViewModel(
    private val detailDate: LocalDate,
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
) : WeatherViewModel(forecastRepository, unitProvider) {

    val weather by lazyDeffered {
        forecastRepository.getFutureWeatherByDate(detailDate, super.isMetricUnit)
    }
}