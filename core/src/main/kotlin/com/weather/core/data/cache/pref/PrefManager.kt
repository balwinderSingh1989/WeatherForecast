package com.weather.core.data.cache.pref

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.weather.core.utility.constants.default_api_id
import javax.inject.Inject

class PrefManager @Inject constructor(context: Context) : PreferenceHelper {


    companion object {
        private const val PREF_KEY_API = "PREF_KEY_API"
        private const val PREF_KEY_LAT = "PREF_KEY_LAT"
        private const val PREF_KEY_LON = "PREF_KEY_LON"
    }


    private val prefs = PreferenceManager.getDefaultSharedPreferences(context)


    override fun getAPIKey(): String {
        return prefs.getString(PREF_KEY_API, default_api_id)

    }

    override fun setAPIKey(userId: String) {
        prefs.setValue(PREF_KEY_API, userId)
    }



    override fun getLastSavedLat(): String {
        return prefs.getString(PREF_KEY_LAT, "")
    }

    override fun getLastSavedLon(): String {
        return prefs.getString(PREF_KEY_LON, "")
    }

    override fun setLastKnownLat(lat: String) {
        prefs.setValue(PREF_KEY_LAT, lat)
    }

    override fun setLastKnownLon(lon: String) {
        prefs.setValue(PREF_KEY_LON, lon)
    }


    fun SharedPreferences.setValue(key: String, value: Any?) {

        when (value) {
            is String -> edit { it.putString(key, value) }
            is Int -> edit { it.putInt(key, value) }
            is Boolean -> edit { it.putBoolean(key, value) }
            is Float -> edit { it.putFloat(key, value) }
            is Long -> edit { it.putLong(key, value) }
            else -> throw UnsupportedOperationException("Unimplemented type error for prefs")
        }

    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        operation(editor)
        editor.apply()
    }


}