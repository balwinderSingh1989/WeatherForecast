package com.weather.core.utility.constants

import android.os.Looper


fun isOnMainThread() = Looper.myLooper() == Looper.getMainLooper()


const val default_api_id = "d4a415361e5a9a564fe15bbf7f34b618"


val WEATHER_STATUS = arrayOf(
    "Thunderstorm",
    "Drizzle",
    "Rain",
    "Snow",
    "Atmosphere",
    "Clear",
    "Few Clouds",
    "Broken Clouds",
    "Cloud"
)

  val DAYS_OF_WEEK =
    arrayOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")

/**
 * Get weather status string according to weather status code
 *
 * @param weatherCode weather status code
 * @return String weather status
 */
fun getWeatherStatus(weatherCode: Int): String {
    when {
        weatherCode / 100 == 2 -> return WEATHER_STATUS[0]
        weatherCode / 100 == 3 -> return WEATHER_STATUS[1]
        weatherCode / 100 == 5 -> return WEATHER_STATUS[2]
        weatherCode / 100 == 6 -> return WEATHER_STATUS[3]
        weatherCode / 100 == 7 -> return WEATHER_STATUS[4]
        weatherCode == 800 -> return WEATHER_STATUS[5]
        weatherCode == 801 -> return WEATHER_STATUS[6]
        weatherCode == 803 -> return WEATHER_STATUS[7]
        weatherCode / 100 == 8 -> return WEATHER_STATUS[8]
        else -> return WEATHER_STATUS[4]
    }
}
