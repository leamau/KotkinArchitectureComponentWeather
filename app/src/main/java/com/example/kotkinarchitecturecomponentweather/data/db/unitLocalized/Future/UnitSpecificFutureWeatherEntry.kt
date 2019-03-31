package com.example.kotkinarchitecturecomponentweather.data.db.unitLocalized.Future

import java.time.LocalDate

interface UnitSpecificFutureWeatherEntry {
    val date :LocalDate
    val avgTemperature :Double
    val conditionText: String
    val conditionIconUrl:String
}