package com.example.kotkinarchitecturecomponentweather.data.network.Response

import com.example.kotkinarchitecturecomponentweather.data.db.entity.FutureWeatherEntry
import com.google.gson.annotations.SerializedName

data class ForecastDaysContainer(
    @SerializedName("forecastday")
    val entries: List<FutureWeatherEntry>
)