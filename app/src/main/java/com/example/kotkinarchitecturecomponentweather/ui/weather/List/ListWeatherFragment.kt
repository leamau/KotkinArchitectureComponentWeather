package com.example.kotkinarchitecturecomponentweather.ui.weather.List

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotkinarchitecturecomponentweather.R
import com.example.kotkinarchitecturecomponentweather.data.db.LocalDateConverter
import com.example.kotkinarchitecturecomponentweather.data.db.unitLocalized.Future.list.UnitSpecificSimpleFutureWeatherEntry
import com.example.kotkinarchitecturecomponentweather.ui.base.ScopedFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.list_weather_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import org.threeten.bp.LocalDate
import java.lang.Appendable


class ListWeatherFragment : ScopedFragment() ,KodeinAware{
    override val kodein by closestKodein()
    private val viewModelFactory:FutureListWeatherViewModelFactory by instance()

    private lateinit var viewModel: ListWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this,viewModelFactory)
            .get(ListWeatherViewModel::class.java)
        bindUI()
    }

    private fun bindUI() = launch(Dispatchers.Main){
        val fetchfutureWeatherEntries = viewModel.weatherEntries.await()

        fetchfutureWeatherEntries.observe(this@ListWeatherFragment, Observer{ weatherEntries->
            if(weatherEntries == null) return@Observer

            group_loading.visibility = View.GONE

            updateDateToNextWeek()
            initRecyclerView(weatherEntries.toFutureWeatherItems())
        })
    }

    private fun updateDateToNextWeek(){
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = "Semaine Prochaine"
    }

    private fun List<UnitSpecificSimpleFutureWeatherEntry>.toFutureWeatherItems() : List<FutureWeatherItem>{
        return this.map {
            FutureWeatherItem(it)
        }
    }

    private fun initRecyclerView(items:List<FutureWeatherItem>){
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(items)
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@ListWeatherFragment.context)
            adapter=groupAdapter
        }

        groupAdapter.setOnItemClickListener{item, view ->
            (item as? FutureWeatherItem)?.let{
                showWeatherDetail(it.weatherEntry.date,view)
            }
        }
    }
    private fun showWeatherDetail(date: LocalDate, view: View) {
        val dateString = LocalDateConverter.dateToString(date)!!
        val actionDetail = ListWeatherFragmentDirections.actionDetail(dateString)
        Navigation.findNavController(view).navigate(actionDetail)
    }
}
