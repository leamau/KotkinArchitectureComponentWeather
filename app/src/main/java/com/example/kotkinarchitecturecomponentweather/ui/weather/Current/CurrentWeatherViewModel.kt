package com.example.kotkinarchitecturecomponentweather.ui.weather.Current

import androidx.lifecycle.ViewModel;
import com.example.kotkinarchitecturecomponentweather.Internal.UnitSystem
import com.example.kotkinarchitecturecomponentweather.Internal.lazyDeffered
import com.example.kotkinarchitecturecomponentweather.data.Repository.ForecastRepository

class CurrentWeatherViewModel(
    private  val forecastRepository:ForecastRepository
) : ViewModel() {
    private val unit = UnitSystem.METRIC //récupéré dans les settings plus tard
    val isMetric :Boolean
        get() = unit == UnitSystem.METRIC
    val weather by lazyDeffered {
        forecastRepository.getCurrentWeather(isMetric)
    }
}
