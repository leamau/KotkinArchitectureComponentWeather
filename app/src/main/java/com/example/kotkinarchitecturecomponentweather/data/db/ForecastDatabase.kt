package com.example.kotkinarchitecturecomponentweather.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kotkinarchitecturecomponentweather.data.db.entity.CurrentWeatherEntry
import com.example.kotkinarchitecturecomponentweather.data.network.Response.FutureWeatherResponse

@Database(
    entities = [CurrentWeatherEntry::class],
    version = 1
)

abstract class ForecastDatabase :RoomDatabase() {
    abstract fun currentWeatherDao(): CurrentWeatherDao
    abstract fun futureWeatherDao():FutureWeatherDao

    companion object {
        @Volatile private var instance :ForecastDatabase? = null
        private  var LOCK = Any()

        operator  fun invoke(context: Context) = instance?: synchronized(LOCK){
            instance ?: buildDatabase(context).also{ instance = it}
        }

        private fun buildDatabase(context: Context)=
                Room.databaseBuilder(context.applicationContext,
                    ForecastDatabase::class.java,"forecast.db")
                    .build()
    }
}