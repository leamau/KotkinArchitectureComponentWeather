package com.example.kotkinarchitecturecomponentweather.ui.weather.List

import android.content.ClipData
import com.example.kotkinarchitecturecomponentweather.Internal.Glide.GlideApp
import com.example.kotkinarchitecturecomponentweather.R
import com.example.kotkinarchitecturecomponentweather.data.db.unitLocalized.Future.list.MetricSimpleFutureWeatherEntry
import com.example.kotkinarchitecturecomponentweather.data.db.unitLocalized.Future.list.UnitSpecificSimpleFutureWeatherEntry
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_future_weather.*
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle

class FutureWeatherItem (
    val weatherEntry:UnitSpecificSimpleFutureWeatherEntry
): Item(){
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            textView_condition.text = weatherEntry.conditionText
            updateDate()
            updateConditionImg()
            updateTemperature()
        }
    }

    override fun getLayout()= R.layout.item_future_weather

    private fun ViewHolder.updateDate(){
    val dtFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
        textView_date.text = weatherEntry.date.format(dtFormatter)
    }

    private fun ViewHolder.updateTemperature() {
        val unitAbbreviation = if (weatherEntry is MetricSimpleFutureWeatherEntry) "°C"
        else "°F"
        textView_temperature.text = weatherEntry.avgTemperature.toString()+unitAbbreviation
    }

    private fun ViewHolder.updateConditionImg(){
        GlideApp.with(this.containerView)
            .load("http:" + weatherEntry.conditionIconUrl)
            .into(imageView_condition_icon)
    }
}