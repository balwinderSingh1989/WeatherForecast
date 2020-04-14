package com.weather.core.data.repository.forecastWeather.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Main (

    @SerializedName("temp")
    var temp: Double = 0.toDouble(),

    @SerializedName("temp_min")
    var tempMin: Double = 0.toDouble(),

    @SerializedName("grnd_level")
    var grndLevel: Double = 0.toDouble(),

    @SerializedName("temp_kf")
    var tempKf: Double = 0.toDouble(),

    @SerializedName("humidity")
    var humidity: Int = 0,

    @SerializedName("pressure")
    var pressure: Double = 0.toDouble(),

    @SerializedName("sea_level")
    var seaLevel: Double = 0.toDouble(),

    @SerializedName("temp_max")
    var tempMax: Double = 0.toDouble()
) : Parcelable