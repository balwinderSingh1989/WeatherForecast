package com.weather.core.data.repository.currentweather.model

import com.google.gson.annotations.SerializedName
import com.weather.core.data.repository.currentweather.model.common.Clouds
import com.weather.core.data.repository.currentweather.model.common.Coord
import com.weather.core.data.repository.currentweather.model.common.WeatherItem
import com.weather.core.data.repository.currentweather.model.common.Wind
import com.weather.core.domain.remote.BaseResponse

data class CurrentWeatherResponse(

    @SerializedName("dt")
    var dt: Int = 0,

    @SerializedName("coord")
    var coord: Coord? = null,

    @SerializedName("weather")
    var weather: List<WeatherItem>? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("cod")
    var cod: Int = 0,

    @SerializedName("main")
    var main: Main? = null,

    @SerializedName("clouds")
    var clouds: Clouds? = null,

    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("sys")
    var sys: Sys? = null,

    @SerializedName("base")
    var base: String? = null,

    @SerializedName("wind")
    var wind: Wind? = null
) : BaseResponse()