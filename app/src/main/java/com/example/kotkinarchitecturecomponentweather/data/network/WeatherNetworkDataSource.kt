package com.example.kotkinarchitecturecomponentweather.data.network

import androidx.lifecycle.LiveData
import com.example.kotkinarchitecturecomponentweather.data.network.Response.CurrentWeatherResponse

interface WeatherNetworkDataSource {
    val downloadedCurrentWeather : LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrentWeather(
        location: String,
        languageCode: String
    )
}