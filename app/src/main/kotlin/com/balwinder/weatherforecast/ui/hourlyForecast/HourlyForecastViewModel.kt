package com.balwinder.weatherforecast.ui.hourlyForecast

import androidx.databinding.ObservableField
import com.balwinder.weatherforecast.base.BaseViewModel
import com.balwinder.weatherforecast.ui.forecast.HourlyNavigator
import com.weather.core.data.repository.forecastWeather.model.ItemHourly
import javax.inject.Inject


class HourlyForecastViewModel @Inject constructor(
) : BaseViewModel<HourlyNavigator>() {


    var dayWeatherItem = ObservableField<ItemHourly>()


}




