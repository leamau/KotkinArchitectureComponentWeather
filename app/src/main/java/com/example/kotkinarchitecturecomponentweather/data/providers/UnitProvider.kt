package com.example.kotkinarchitecturecomponentweather.data.providers

import com.example.kotkinarchitecturecomponentweather.Internal.UnitSystem

interface UnitProvider {
    fun getUnitSystem(): UnitSystem
}