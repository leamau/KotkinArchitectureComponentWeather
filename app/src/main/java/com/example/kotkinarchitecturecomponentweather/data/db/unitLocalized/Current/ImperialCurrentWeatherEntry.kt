package com.example.kotkinarchitecturecomponentweather.data.db.unitLocalized.Current

import androidx.room.ColumnInfo

data class ImperialCurrentWeatherEntry (
    @ColumnInfo(name = "tempF")
    override val temperature: Double,
    @ColumnInfo(name = "condition_text")
    override val conditionText: String,
    @ColumnInfo(name = "condition_icon")
    override val conditionIconUrl: String,
    @ColumnInfo(name = "windMph")
    override val windSpeed: Double,
    @ColumnInfo(name = "windDir")
    override val windDirection: String,
    @ColumnInfo(name = "precipIn")
    override val precipitationVolume: Double,
    @ColumnInfo(name = "feelslikeF")
    override val feeLikeTemperature: Double,
    @ColumnInfo(name = "visMiles")
    override val visibilityDistance: Double


) : UnitSpecificCurrentWeatherEntry