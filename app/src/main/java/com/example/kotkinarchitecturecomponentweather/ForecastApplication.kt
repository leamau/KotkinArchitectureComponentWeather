package com.example.kotkinarchitecturecomponentweather

import android.app.Application
import androidx.preference.PreferenceManager
import com.example.kotkinarchitecturecomponentweather.data.Repository.ForecastRepository
import com.example.kotkinarchitecturecomponentweather.data.Repository.ForecatRepositoryImpl
import com.example.kotkinarchitecturecomponentweather.data.db.ForecastDatabase
import com.example.kotkinarchitecturecomponentweather.data.network.*
import com.example.kotkinarchitecturecomponentweather.data.providers.UnitProvider
import com.example.kotkinarchitecturecomponentweather.data.providers.UnitProviderImpl
import com.example.kotkinarchitecturecomponentweather.ui.weather.Current.CurrentWeatherViewModelFactory
import com.example.kotkinarchitecturecomponentweather.ui.weather.Detail.DetailWeatherViewModel
import com.example.kotkinarchitecturecomponentweather.ui.weather.List.FutureListWeatherViewModelFactory
import com.example.kotkinarchitecturecomponentweather.ui.weather.List.ListWeatherViewModel
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.androidModule
import org.kodein.di.generic.*
import org.threeten.bp.LocalDate


class ForecastApplication : Application(), KodeinAware{
    override val kodein= Kodein.lazy {
        import(androidModule(this@ForecastApplication))

        bind() from singleton { ForecastDatabase(instance()) }
        bind() from singleton { instance<ForecastDatabase>().currentWeatherDao() }
        bind() from singleton { instance<ForecastDatabase>().futureWeatherDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { WeatherApiService(instance()) }
        bind<WeatherNetworkDataSource>() with singleton { WeatherNetworkDataSourceImpl(instance()) }
        bind<ForecastRepository>() with singleton { ForecatRepositoryImpl(instance(),instance(),instance()) }
        bind<UnitProvider>() with singleton { UnitProviderImpl(instance()) }
        bind() from provider { CurrentWeatherViewModelFactory(instance(),instance()) }
        bind() from provider { FutureListWeatherViewModelFactory(instance(),instance()) }
        bind() from factory { detailDate: LocalDate -> DetailWeatherViewModel(detailDate,instance(),instance())}
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        PreferenceManager.setDefaultValues(this,R.xml.preferences,false)
    }

}