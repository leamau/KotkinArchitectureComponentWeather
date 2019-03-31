package com.example.kotkinarchitecturecomponentweather.data.Repository

import androidx.lifecycle.LiveData
import com.example.kotkinarchitecturecomponentweather.data.db.unitLocalized.Current.UnitSpecificCurrentWeatherEntry
import com.example.kotkinarchitecturecomponentweather.data.db.unitLocalized.Future.Detail.UnitSpecificDetailFutureWeatherEntry
import com.example.kotkinarchitecturecomponentweather.data.db.unitLocalized.Future.list.UnitSpecificSimpleFutureWeatherEntry
import org.threeten.bp.LocalDate

interface ForecastRepository {
    suspend fun getCurrentWeather(metric:Boolean): LiveData<out UnitSpecificCurrentWeatherEntry>
    suspend fun getFutureWeatherByDate(date: LocalDate, metric: Boolean): LiveData<out UnitSpecificDetailFutureWeatherEntry>
    suspend fun getFutureWeatherList(startDate: LocalDate, metric: Boolean):LiveData<out List<UnitSpecificSimpleFutureWeatherEntry>>

}