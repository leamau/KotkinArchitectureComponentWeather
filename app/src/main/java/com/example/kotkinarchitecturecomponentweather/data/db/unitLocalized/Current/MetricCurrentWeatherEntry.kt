package com.example.kotkinarchitecturecomponentweather.data.db.unitLocalized.Current

import androidx.room.ColumnInfo

data class MetricCurrentWeatherEntry(
    @ColumnInfo(name = "tempC")
    override val temperature: Double,
    @ColumnInfo(name = "condition_text")
    override val conditionText: String,
    @ColumnInfo(name = "condition_icon")
    override val conditionIconUrl: String,
    @ColumnInfo(name = "windKph")
    override val windSpeed: Double,
    @ColumnInfo(name = "windDir")
    override val windDirection: String,
    @ColumnInfo(name = "precipMm")
    override val precipitationVolume: Double,
    @ColumnInfo(name = "feelslikeC")
    override val feeLikeTemperature: Double,
    @ColumnInfo(name = "visKm")
    override val visibilityDistance: Double


) : UnitSpecificCurrentWeatherEntry