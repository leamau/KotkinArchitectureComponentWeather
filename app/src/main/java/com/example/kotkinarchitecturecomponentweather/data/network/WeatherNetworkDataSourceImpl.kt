package com.example.kotkinarchitecturecomponentweather.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotkinarchitecturecomponentweather.Internal.NoConnectivityExeption
import com.example.kotkinarchitecturecomponentweather.data.network.Response.CurrentWeatherResponse

class WeatherNetworkDataSourceImpl(
    private val WeatherApiService: WeatherApiService
) : WeatherNetworkDataSource {

    private val _downloadedCurrentWeather = MutableLiveData<CurrentWeatherResponse>()

    override suspend fun fetchCurrentWeather(location: String, languageCode: String) {
        try {
            val fetchedCurrentWeather = WeatherApiService
                .getCurrentWeather(location,languageCode)
                .await()
            _downloadedCurrentWeather.postValue(fetchedCurrentWeather)
        }catch (e: NoConnectivityExeption){
            Log.e("No Connectivity","no internet connection",e)
        }
    }

    override val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>
        get() = _downloadedCurrentWeather
}