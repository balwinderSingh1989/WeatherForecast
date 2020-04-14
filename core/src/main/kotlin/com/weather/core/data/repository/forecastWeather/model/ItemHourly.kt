package com.weather.core.data.repository.forecastWeather.model

import android.graphics.Color
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.weather.core.data.repository.currentweather.model.common.Clouds
import com.weather.core.data.repository.currentweather.model.common.WeatherItem
import com.weather.core.data.repository.currentweather.model.common.Wind
import com.weather.core.utility.constants.DAYS_OF_WEEK
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class ItemHourly(

    @SerializedName("dt")
    var dt: Int = 0,

    @SerializedName("dt_txt")
    var dtTxt: String? = null,

    @SerializedName("weather")
    var weather: List<WeatherItem>? = null,

    @SerializedName("main")
    var main: Main? = null,

    @SerializedName("clouds")
    var clouds: Clouds? = null,

    @SerializedName("sys")
    var sys: Sys? = null,

    @SerializedName("wind")
    var wind: Wind? = null,

    @SerializedName("rain")
    var rain: Rain? = null,


    var itemColor: Int? = null,

   //var dayOftheWeek: String?,

    var weatherIcon : Int
) : Parcelable {

    fun getHourColor(): Int {
        return when (dtTxt?.substringAfter(" ")?.substringBeforeLast(":")) {
            "00:00" -> Color.parseColor("#28E0AE")
            "03:00" -> Color.parseColor("#FF0090")
            "06:00" -> Color.parseColor("#FFAE00")
            "09:00" -> Color.parseColor("#0090FF")
            "12:00" -> Color.parseColor("#DC0000")
            "15:00" -> Color.parseColor("#0051FF")
            "18:00" -> Color.parseColor("#3D28E0")
            "21:00" -> Color.parseColor("#50E3FE")
            else -> Color.parseColor("#28E0AE")
        }
    }


    fun getDayOftheWeek(): String {

        val calendar = Calendar.getInstance(TimeZone.getDefault())
        calendar.timeInMillis = dt * 1000L

        return DAYS_OF_WEEK[calendar.get(Calendar.DAY_OF_WEEK) - 1]

    }

    fun getHourOfDay(): String {
        return dtTxt?.substringAfter(" ")?.substringBeforeLast(":") ?: "00:00"
    }




}


