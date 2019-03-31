package com.example.kotkinarchitecturecomponentweather.data.Repository

import androidx.lifecycle.LiveData
import com.example.kotkinarchitecturecomponentweather.data.db.CurrentWeatherDao
import com.example.kotkinarchitecturecomponentweather.data.db.FutureWeatherDao
import com.example.kotkinarchitecturecomponentweather.data.db.unitLocalized.Current.UnitSpecificCurrentWeatherEntry
import com.example.kotkinarchitecturecomponentweather.data.db.unitLocalized.Future.list.UnitSpecificSimpleFutureWeatherEntry
import com.example.kotkinarchitecturecomponentweather.data.network.FORECAST_DAYS_COUNT
import com.example.kotkinarchitecturecomponentweather.data.network.Response.CurrentWeatherResponse
import com.example.kotkinarchitecturecomponentweather.data.network.Response.FutureWeatherResponse
import com.example.kotkinarchitecturecomponentweather.data.network.WeatherNetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDate
import java.time.ZonedDateTime
import java.util.*

class ForecatRepositoryImpl(
    private val currentWeatherDao: CurrentWeatherDao,
    private val weatherNetworkDataSource: WeatherNetworkDataSource,
    private val futureWeatherDao: FutureWeatherDao
) : ForecastRepository {
    init {
        weatherNetworkDataSource.apply {
            downloadedCurrentWeather.observeForever {newCurrentWeather ->
                persitFetchedCurrentWeather(newCurrentWeather)
            }
            downloadedFutureWeather.observeForever {newFutureWeather ->
                persitFetchedFutureWeather(newFutureWeather)
            }
        }
    }
    private suspend fun initWeatherDatas(){
        if(isFetchCurrentNeeder(ZonedDateTime.now().minusHours(1)))
            fetchCurrentWeather()
        if(isFetchFutureNeeded())
            fetchFutureWeather()
    }

    private suspend fun fetchCurrentWeather(){
        weatherNetworkDataSource.fetchCurrentWeather(
            "Toulouse",
            Locale.getDefault().language
        )
    }

    private suspend fun fetchFutureWeather(){
        weatherNetworkDataSource.fetchFutureWeather(
            "Toulouse",
            Locale.getDefault().language
        )
    }

    private  fun isFetchCurrentNeeder(lastFetchTime:ZonedDateTime) : Boolean{
        val trenteminuteAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(trenteminuteAgo)
    }

    private  fun isFetchFutureNeeded() : Boolean{
        val today = LocalDate.now()
        val futureWeatherCount = futureWeatherDao.countFutureWeather(today)
        return futureWeatherCount< FORECAST_DAYS_COUNT
    }


    override suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry> {
       return withContext(Dispatchers.IO){
           initWeatherDatas()
           return@withContext if (metric) currentWeatherDao.getWeatherMertric()
            else currentWeatherDao.getWeatherImperial()
       }
    }

    override suspend fun getFutureWeatherList(
        startDate: LocalDate,
        metric: Boolean
    ): LiveData<out List<UnitSpecificSimpleFutureWeatherEntry>> {
        return withContext(Dispatchers.IO){
            initWeatherDatas()
            return@withContext if (metric) futureWeatherDao.getSimpleWeatherForecastsMetric(startDate)
            else futureWeatherDao.getSimpleWeatherForecastsImperial(startDate)
        }
    }

    private fun persitFetchedCurrentWeather(fetchedWeather:CurrentWeatherResponse){
        GlobalScope.launch(Dispatchers.IO ) {
            currentWeatherDao.upsert(fetchedWeather.currentWeatherEntry)
        }
    }

    private fun persitFetchedFutureWeather(fetchedWeather: FutureWeatherResponse){
        fun deleteOldForecastData(){
            val futureWeatherList = fetchedWeather.futureWeatherEntries.entries
            futureWeatherDao.insert(futureWeatherList)
        }
        GlobalScope.launch(Dispatchers.IO ) {
            deleteOldForecastData()
            val futureWeatherList = fetchedWeather.futureWeatherEntries.entries
            futureWeatherDao.insert(futureWeatherList)
        }
    }
}