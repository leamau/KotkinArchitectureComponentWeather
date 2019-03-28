package com.example.kotkinarchitecturecomponentweather.data.network.Response

import com.example.kotkinarchitecturecomponentweather.data.db.entity.CurrentWeatherEntry
import com.example.kotkinarchitecturecomponentweather.data.db.entity.Location
import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
    val location: Location,
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry
)