package com.example.kotkinarchitecturecomponentweather.data.Response

import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
    val location: Location,
    @SerializedName("current")
    val CurrentWeatherEntry: CurrentWeatherEntry
)