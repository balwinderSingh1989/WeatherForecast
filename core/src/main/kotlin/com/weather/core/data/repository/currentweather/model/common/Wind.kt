package com.weather.core.data.repository.currentweather.model.common

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Wind (

    @SerializedName("deg")
    var deg: Double = 0.toDouble(),

    @SerializedName("speed")
    var speed: Double = 0.toDouble()

): Parcelable