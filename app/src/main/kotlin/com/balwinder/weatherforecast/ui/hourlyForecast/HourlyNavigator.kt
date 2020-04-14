package com.balwinder.weatherforecast.ui.forecast

import com.balwinder.weatherforecast.base.BaseNavigator

interface HourlyNavigator : BaseNavigator {


    fun showAlert(stringId: Int, message: String)
}