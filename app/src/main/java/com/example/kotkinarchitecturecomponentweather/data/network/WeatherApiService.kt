package com.example.kotkinarchitecturecomponentweather.data.network

import com.example.kotkinarchitecturecomponentweather.data.network.Response.CurrentWeatherResponse
import com.example.kotkinarchitecturecomponentweather.data.network.Response.FutureWeatherResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val token = "2481daf063a94cf7b41115325192803"

interface WeatherApiService {

    //http://api.apixu.com/v1/current.json?key=2481daf063a94cf7b41115325192803&q=Toulouse&lang=fr
    @GET("current.json")
    fun getCurrentWeather(
        @Query("q")location: String,
        @Query("lang") languageCode: String = "fr"
    ) : Deferred<CurrentWeatherResponse>

    //http://api.apixu.com/v1/forecast.json?key=2481daf063a94cf7b41115325192803&q=Toulouse&days=7
    @GET("forecast.json")
    fun getFutureWeather(
        @Query("q") location:String,
        @Query("days")days:Int,
        @Query("lang") languageCode: String = "fr"
    ) : Deferred<FutureWeatherResponse>

    companion object {
        operator  fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ) : WeatherApiService {
            val requestInterceptor = Interceptor{ chain ->
                val uri = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("key",
                        token
                    )
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(uri)
                    .build()

                return@Interceptor chain.proceed((request))
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor((requestInterceptor))
                .addInterceptor(connectivityInterceptor)
                .build()


            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.apixu.com/v1/")
                .addCallAdapterFactory((CoroutineCallAdapterFactory()))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherApiService::class.java)
        }
    }

}