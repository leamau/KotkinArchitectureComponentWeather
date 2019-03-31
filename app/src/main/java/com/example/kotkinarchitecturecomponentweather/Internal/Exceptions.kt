package com.example.kotkinarchitecturecomponentweather.Internal

import java.io.IOException

class NoConnectivityExeption: IOException()
class LocationPermissionNotGrantedException: Exception()
class DateNotFoundException: Exception()