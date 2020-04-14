package com.weather.core.data.cache.pref

interface PreferenceHelper {

    fun getAPIKey(): String

    fun setAPIKey(userId: String)

    fun getLastSavedLat(): String
    fun getLastSavedLon(): String
    fun setLastKnownLat(lat: String)
    fun setLastKnownLon(lon: String)

}