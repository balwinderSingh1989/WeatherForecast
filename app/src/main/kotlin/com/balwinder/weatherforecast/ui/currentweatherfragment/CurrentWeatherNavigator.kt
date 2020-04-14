package com.balwinder.weatherforecast.ui.currentweatherfragment

import com.balwinder.weatherforecast.base.BaseNavigator

interface CurrentWeatherNavigator : BaseNavigator {


    fun showAlert(stringId: Int, message: String)
}