package com.weather.core.data.repository.currentweather.model.common

import com.google.gson.annotations.SerializedName

data class Coord (

    @SerializedName("lon")
    var lon: Double = 0.toDouble(),

    @SerializedName("lat")
    var lat: Double = 0.toDouble()
)