package com.weather.core.data.repository.currentweather.model

import com.google.gson.annotations.SerializedName

data class Sys (

    @SerializedName("country")
    var country: String? = null,

    @SerializedName("sunrise")
    var sunrise: Int = 0,

    @SerializedName("sunset")
    var sunset: Int = 0,

    @SerializedName("message")
    var message: Double = 0.toDouble()

)