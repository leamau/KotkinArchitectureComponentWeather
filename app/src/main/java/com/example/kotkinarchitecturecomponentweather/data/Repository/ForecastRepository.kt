package com.example.kotkinarchitecturecomponentweather.data.Repository

import androidx.lifecycle.LiveData
import com.example.kotkinarchitecturecomponentweather.data.db.unitLocalized.UnitSpecificCurrentWeatherEntry

interface ForecastRepository {
    suspend fun getCurrentWeather(metric:Boolean): LiveData<out UnitSpecificCurrentWeatherEntry>
}