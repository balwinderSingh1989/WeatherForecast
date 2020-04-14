package com.weather.core.data.cache

import com.weather.core.data.cache.pref.PreferenceHelper
import javax.inject.Inject

class AppDataManager @Inject constructor(private val preferenceHelper: PreferenceHelper) :
    DataManager() {


    override fun updateLastKnownLocation(lat: String, lon: String) {
        setLastKnownLat(lat)
        setLastKnownLon(lon)
    }


    override fun udpateAPIKey(apiKey: String) {
       setAPIKey(apiKey)
    }


    override fun setAPIKey(userId: String) {
        preferenceHelper.setAPIKey(userId)
    }

    override fun getAPIKey(): String {
        return preferenceHelper.getAPIKey()
    }

    override fun getLastSavedLat(): String {
        return preferenceHelper.getLastSavedLat()
    }

    override fun getLastSavedLon(): String {
        return preferenceHelper.getLastSavedLon()
    }

    override fun setLastKnownLat(lat: String) {
        preferenceHelper.setLastKnownLat(lat)
    }

    override fun setLastKnownLon(lon: String) {
        preferenceHelper.setLastKnownLon(lon)
    }



}