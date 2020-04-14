package com.balwinder.weatherforecast.ui.forecast

import com.balwinder.weatherforecast.base.BaseNavigator

interface ForecastNavigator : BaseNavigator {


    fun showAlert(stringId: Int, message: String)
}