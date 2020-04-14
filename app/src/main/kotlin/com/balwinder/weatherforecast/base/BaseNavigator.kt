package com.balwinder.weatherforecast.base

interface BaseNavigator {


    fun isConnected(): Boolean

    fun showLoading(message: String)

    fun hideLoading()

    fun setProgressMessage(message: String)


}