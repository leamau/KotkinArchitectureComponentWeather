package com.example.kotkinarchitecturecomponentweather.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kotkinarchitecturecomponentweather.data.db.entity.CurrentWeatherEntry
import com.example.kotkinarchitecturecomponentweather.data.db.entity.Current_Weather_ID
import com.example.kotkinarchitecturecomponentweather.data.db.unitLocalized.Current.ImperialCurrentWeatherEntry
import com.example.kotkinarchitecturecomponentweather.data.db.unitLocalized.Current.MetricCurrentWeatherEntry

@Dao
interface CurrentWeatherDao {

    //un conflit sera généré des qu'il y aura deux currentWeatherEntry avec le même id
    //comme l'id est toujours le même
    //on remplace a chaque fois l'ancien par le nouveau
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(WeatherEntry:CurrentWeatherEntry)
    @Query("SELECT * FROM current_weather WHERE id = $Current_Weather_ID")
    fun getWeatherMertric(): LiveData<MetricCurrentWeatherEntry>

    @Query("SELECT * FROM current_weather WHERE id = $Current_Weather_ID")
    fun getWeatherImperial(): LiveData<ImperialCurrentWeatherEntry>

}