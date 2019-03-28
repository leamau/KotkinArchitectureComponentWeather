package com.example.kotkinarchitecturecomponentweather.data

import com.example.kotkinarchitecturecomponentweather.data.network.Response.CurrentWeatherResponse
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

    companion object {
        operator  fun invoke() : WeatherApiService{
            val requestInterceptor = Interceptor{ chain ->
                val uri = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("key", token)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(uri)
                    .build()

                return@Interceptor chain.proceed((request))
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor((requestInterceptor))
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