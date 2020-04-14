package com.weather.core.data.repository.forecastWeather.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Rain (

    @SerializedName("3h")
    var jsonMember3h: Double = 0.toDouble()
): Parcelable