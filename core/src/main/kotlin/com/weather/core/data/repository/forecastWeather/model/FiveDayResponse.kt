package com.weather.core.data.repository.forecastWeather.model

import com.google.gson.annotations.SerializedName
import com.weather.core.domain.remote.BaseResponse

data class FiveDayResponse (

    @SerializedName("city")
    var city: City? = null,

    @SerializedName("cnt")
    var cnt: Int = 0,

    @SerializedName("cod")
    var cod: String? = null,

    @SerializedName("message")
    var message: Double = 0.toDouble(),

    @SerializedName("list")
    var list: List<ItemHourly>? = null
)  : BaseResponse()