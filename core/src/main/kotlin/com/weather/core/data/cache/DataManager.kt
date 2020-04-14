package com.weather.core.data.cache

import com.weather.core.data.cache.pref.PreferenceHelper

abstract class DataManager : PreferenceHelper {

    abstract fun udpateAPIKey(apiKey: String)
    abstract fun updateLastKnownLocation(lat: String, lon : String)
}
