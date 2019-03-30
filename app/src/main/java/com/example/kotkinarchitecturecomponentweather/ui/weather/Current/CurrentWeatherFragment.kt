package com.example.kotkinarchitecturecomponentweather.ui.weather.Current

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.kotkinarchitecturecomponentweather.Internal.Glide.GlideApp
import com.example.kotkinarchitecturecomponentweather.R
import com.example.kotkinarchitecturecomponentweather.data.network.ConnectivityInterceptorImpl
import com.example.kotkinarchitecturecomponentweather.data.network.Response.CurrentWeatherResponse
import com.example.kotkinarchitecturecomponentweather.data.network.WeatherApiService
import com.example.kotkinarchitecturecomponentweather.data.network.WeatherNetworkDataSource
import com.example.kotkinarchitecturecomponentweather.data.network.WeatherNetworkDataSourceImpl
import com.example.kotkinarchitecturecomponentweather.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class CurrentWeatherFragment : ScopedFragment(),KodeinAware {
    override val kodein by closestKodein()
    private val viewModelFactory:CurrentWeatherViewModelFactory by instance()

    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this,viewModelFactory)
            .get(CurrentWeatherViewModel::class.java)

        bindUI()

    }

    private fun bindUI() = launch{
        val currentWeather = viewModel.weather.await()
        currentWeather.observe(this@CurrentWeatherFragment,Observer{
            if(it==null)return@Observer

            group_loading.visibility = View.GONE
            updateLocation("Toulouse")
            updateDateToday()
            updateTemperature(it.temperature,it.feeLikeTemperature)
            updateCondition(it.conditionText)
            updatePrecipitation(it.precipitationVolume)
            updateWind(it.windDirection,it.windSpeed)
            updateVisibility(it.visibilityDistance)

            GlideApp.with(this@CurrentWeatherFragment)
                .load("http:${it.conditionIconUrl}")
                .into(imageView_condition_icon)
        })
    }

    private  fun chooseUnitAbbreviation(metric:String,imperial:String):String{
        return if (viewModel.isMetric) metric else imperial
    }

    private fun updateLocation(location:String){
        (activity as? AppCompatActivity)?.supportActionBar?.title = location

    }

    private fun updateDateToday(){
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = "Today"
    }

    private fun updateTemperature(temperature:Double,feelsLike: Double){
        val unitAbbreviation = chooseUnitAbbreviation("°C","°F")
        textView_temperature.text = "$temperature$unitAbbreviation"
        textView_feels_like_temperature.text = "Ressentis $feelsLike$unitAbbreviation"
    }

    private fun updateCondition(condition:String){
        textView_condition.text = condition
    }

    private fun updatePrecipitation(volume:Double){
        val unitAbbreviation = chooseUnitAbbreviation("mm","in")
        textView_precipitation.text = "Precipitations : $volume $unitAbbreviation"
    }
    private fun updateWind(direction:String,speed:Double){
        val unitAbbreviation = chooseUnitAbbreviation("km/h","mi/h")
        textView_precipitation.text = "Precipitations : $direction, $speed $unitAbbreviation"
    }
    private fun updateVisibility(distance:Double){
        val unitAbbreviation = chooseUnitAbbreviation("km","mi")
        textView_precipitation.text = "Precipitations : $distance $unitAbbreviation"
    }

}
