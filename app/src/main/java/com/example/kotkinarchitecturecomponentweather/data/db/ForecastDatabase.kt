package com.example.kotkinarchitecturecomponentweather.data.db

import android.content.Context
import androidx.room.*
import com.example.kotkinarchitecturecomponentweather.data.db.entity.CurrentWeatherEntry
import com.example.kotkinarchitecturecomponentweather.data.db.entity.FutureWeatherEntry

@Database(
    entities = [CurrentWeatherEntry::class, FutureWeatherEntry::class],
    version = 1
)
@TypeConverters(LocalDateConverter::class)
abstract class ForecastDatabase : RoomDatabase() {
    abstract fun currentWeatherDao(): CurrentWeatherDao
    abstract fun futureWeatherDao(): FutureWeatherDao

    companion object {
        @Volatile private var instance: ForecastDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                ForecastDatabase::class.java, "futureWeatherEntries.db")
                .build()
    }
}