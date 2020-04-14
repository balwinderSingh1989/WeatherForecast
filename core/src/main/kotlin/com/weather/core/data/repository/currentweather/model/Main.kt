 package com.weather.core.data.repository.currentweather.model

import com.google.gson.annotations.SerializedName

data class Main (

    @SerializedName("temp")
    var temp: Double = 0.toDouble(),

    @SerializedName("temp_min")
    var tempMin: Double = 0.toDouble(),

    @SerializedName("grnd_level")
    var grndLevel: Double = 0.toDouble(),

    @SerializedName("humidity")
    var humidity: Int = 0,

    @SerializedName("pressure")
    var pressure: Double = 0.toDouble(),

    @SerializedName("sea_level")
    var seaLevel: Double = 0.toDouble(),

    @SerializedName("temp_max")
    var tempMax: Double = 0.toDouble()

)