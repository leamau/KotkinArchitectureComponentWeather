package com.example.kotkinarchitecturecomponentweather.data.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


const val Current_Weather_ID = 0

@Entity(tableName = "Current_weather")
data class CurrentWeatherEntry(
    //température
    @SerializedName("temp_c")
    val tempC: Double,
    @SerializedName("temp_f")
    val tempF: Double,

    @SerializedName("is_day")
    val isDay: Int,

    @Embedded(prefix = "condition_")
    val condition: Condition,
    //vent
    @SerializedName("wind_mph")
    val windMph: Double,
    @SerializedName("wind_kph")
    val windKph: Double,
    @SerializedName("wind_dir")
    val windDir: String,
    //précipitations
    @SerializedName("precip_mm")
    val precipMm: Double,
    @SerializedName("precip_in")
    val precipIn: Double,
    //ressentis
    @SerializedName("feelslike_c")
    val feelslikeC: Double,
    @SerializedName("feelslike_f")
    val feelslikeF: Double,
    //visibilité
    @SerializedName("vis_km")
    val visKm: Double,
    @SerializedName("vis_miles")
    val visMiles: Double,

    val uv: Int,

    @SerializedName("gust_mph")
    val gustMph: Double,
    @SerializedName("gust_kph")
    val gustKph: Double
){
    @PrimaryKey(autoGenerate = false)
    var id: Int = Current_Weather_ID
}