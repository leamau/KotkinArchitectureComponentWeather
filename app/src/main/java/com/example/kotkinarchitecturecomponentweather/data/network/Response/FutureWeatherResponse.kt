package com.example.kotkinarchitecturecomponentweather.data.network.Response

import com.example.kotkinarchitecturecomponentweather.data.db.entity.Location
import com.google.gson.annotations.SerializedName

data class FutureWeatherResponse(
    @SerializedName("forecast")
    val futureWeatherEntries: ForecastDaysContainer,
    val location: Location
)