package com.example.kotkinarchitecturecomponentweather.ui.weather.List

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotkinarchitecturecomponentweather.R


class ListWeatherFragment : Fragment() {

    companion object {
        fun newInstance() = ListWeatherFragment()
    }

    private lateinit var viewModel: ListWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ListWeatherViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
