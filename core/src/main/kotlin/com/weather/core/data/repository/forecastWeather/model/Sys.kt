package com.weather.core.data.repository.forecastWeather.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Sys (

    @SerializedName("pod")
    var pod: String? = null
): Parcelable