package com.balwinder.weatherforecast.ui.currentweatherfragment.adapter.model

import com.weather.core.data.repository.currentweather.model.CurrentWeatherResponse

data class WeatherList(
    var currentWeather: MutableList<CurrentWeatherResponse> = ArrayList()


)