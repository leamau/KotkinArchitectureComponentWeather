package com.example.kotkinarchitecturecomponentweather.data.Repository

import androidx.lifecycle.LiveData
import com.example.kotkinarchitecturecomponentweather.data.db.CurrentWeatherDao
import com.example.kotkinarchitecturecomponentweather.data.db.unitLocalized.Current.UnitSpecificCurrentWeatherEntry
import com.example.kotkinarchitecturecomponentweather.data.network.Response.CurrentWeatherResponse
import com.example.kotkinarchitecturecomponentweather.data.network.WeatherNetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.ZonedDateTime
import java.util.*

class ForecatRepositoryImpl(
    private val currentWeatherDao: CurrentWeatherDao,
    private val weatherNetworkDataSource: WeatherNetworkDataSource
) : ForecastRepository {
    init {
        weatherNetworkDataSource.downloadedCurrentWeather.observeForever {newCurrentWeather ->
            persitFetchedCurrentWeather(newCurrentWeather)
        }
    }
    private suspend fun initWeatherDatas(){
        if(isFetchCurrentNeeder(ZonedDateTime.now().minusHours(1)))
            fetchCurrentWeather()
    }

    private suspend fun fetchCurrentWeather(){
        weatherNetworkDataSource.fetchCurrentWeather(
            "Toulouse",
            Locale.getDefault().language
        )
    }

    private  fun isFetchCurrentNeeder(lastFetchTime:ZonedDateTime) : Boolean{
        val trenteminuteAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(trenteminuteAgo)
    }


    override suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry> {
       return withContext(Dispatchers.IO){
           initWeatherDatas()
           return@withContext if (metric) currentWeatherDao.getWeatherMertric()
            else currentWeatherDao.getWeatherImperial()
       }
    }

    private fun persitFetchedCurrentWeather(fetchedWeather:CurrentWeatherResponse){
        GlobalScope.launch(Dispatchers.IO ) {
            currentWeatherDao.upsert(fetchedWeather.currentWeatherEntry)
        }
    }
}