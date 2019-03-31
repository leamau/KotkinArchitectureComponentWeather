package com.example.kotkinarchitecturecomponentweather.ui.weather.Detail


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotkinarchitecturecomponentweather.data.Repository.ForecastRepository
import com.example.kotkinarchitecturecomponentweather.data.providers.UnitProvider
import org.threeten.bp.LocalDate


class FutureDetailWeatherViewModelFactory(
    private val detailDate: LocalDate,
    private val forecastRepository: ForecastRepository,
    private val unitProvider: UnitProvider
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailWeatherViewModel(
            detailDate,
            forecastRepository,
            unitProvider
        ) as T
    }
}