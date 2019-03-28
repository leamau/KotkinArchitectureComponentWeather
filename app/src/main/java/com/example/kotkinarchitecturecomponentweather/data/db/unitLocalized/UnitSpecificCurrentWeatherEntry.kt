package com.example.kotkinarchitecturecomponentweather.data.db.unitLocalized

interface UnitSpecificCurrentWeatherEntry {
    val temperature:Double
    val conditionText:String
    val conditionIconUrl:String
    val windSpeed: Double
    val windDirection: String
    val precipitationVolume: Double
    val feeLikeTemperature: Double
    val visibilityDistance: Double
}