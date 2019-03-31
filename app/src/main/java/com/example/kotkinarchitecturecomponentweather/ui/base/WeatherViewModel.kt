package com.example.kotkinarchitecturecomponentweather.ui.base

import androidx.lifecycle.ViewModel
import com.example.kotkinarchitecturecomponentweather.Internal.UnitSystem
import com.example.kotkinarchitecturecomponentweather.Internal.lazyDeffered
import com.example.kotkinarchitecturecomponentweather.data.Repository.ForecastRepository
import com.example.kotkinarchitecturecomponentweather.data.providers.UnitProvider

abstract class WeatherViewModel (
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
):ViewModel(){
    private val unitSystem = unitProvider.getUnitSystem()

    val isMetricUnit: Boolean
    get() = unitSystem == UnitSystem.METRIC

}