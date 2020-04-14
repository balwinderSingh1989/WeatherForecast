package com.weather.core.data.repository.factory

import com.weather.core.data.repository.currentweather.model.CurrentWeatherResponse
import com.weather.core.data.repository.currentweather.model.Main
import com.weather.core.data.repository.currentweather.model.Sys
import com.weather.core.data.repository.currentweather.model.common.Clouds
import com.weather.core.data.repository.currentweather.model.common.Coord
import com.weather.core.data.repository.currentweather.model.common.WeatherItem
import com.weather.core.data.repository.currentweather.model.common.Wind
import com.weather.core.data.repository.forecastWeather.model.City
import com.weather.core.data.repository.forecastWeather.model.FiveDayResponse
import com.weather.core.data.repository.forecastWeather.model.ItemHourly
import com.weather.core.data.repository.forecastWeather.model.Rain
import io.reactivex.Single
import java.net.HttpURLConnection


object DataFactory {

    //
//    fun createSampleForecastWithCoord(id: Int, cityName: String, lat: Double, lon: Double): ForecastEntity {
//        val list = emptyList<LauncherActivity.ListItem>()
//        return Forecast(id, CityEntity("Turkey", CoordEntity(lon, lat), cityName, 34), list)
//    }
//
//    fun generateCitiesForSearchEntity(id: String, name: String): CitiesForSearchEntity {
//        return CitiesForSearchEntity("Clear", "Turkey", CoordEntity(34.0, 30.0), name, "Beyoglu", 1, id)
//    }
//
//    fun generateCurrentWeatherEntity(name: String, id: Int): CurrentWeatherEntity {
//        val weatherItem = WeatherItem("12d", "clouds", "cloud & sun", 1)
//        val weather = listOf(weatherItem)
//        return CurrentWeatherResponse(1, 2, MainEntity(34.0, 30.0, 2.0, 321.0, 21, 132.0, 12.0, 35.0), null, 3421399123, weather, name, id, "Celciues", null)
//    }
//
    fun createSampleForecastResponse(): FiveDayResponse {

        val weatherItem = WeatherItem("12d", "clouds", "cloud & sun", 1)
        val weather = listOf(weatherItem)

        val listItem = ItemHourly(
            123123,
            "12 10 2020",
            weather,
            com.weather.core.data.repository.forecastWeather.model.Main(
                34.0,
                30.0,
                2.0,
                321.0,
                21,
                132.0,
                12.0,
                35.0
            ),
            Clouds(1),
            com.weather.core.data.repository.forecastWeather.model.Sys("a"),
            Wind(12.0, 12.0),

            Rain(12.0),
            0,
            0
        )
        val list = listOf(listItem)
        return FiveDayResponse(
            City("Turkey", Coord(32.32, 30.30), "Istanbul", 10),
            0,
            "200",
            0.0,
            list
        )
    }

    //
    private fun createSampleCurrentWeatherResponse(): CurrentWeatherResponse {
        val weatherItem = WeatherItem("12d", "clouds", "cloud & sun", 1)
        val weather = listOf(weatherItem)
        return CurrentWeatherResponse(
            1212232,
            Coord(32.32, 30.30),
            weather,
            "dubai-Mock",
            200,
            Main(34.0, 30.0, 2.0, 321, 21.00, 132.0, 12.0),
            Clouds(1),
            10,
            Sys("a"),
            null,
            Wind(12.0, 11.0)
        )
    }


    fun generateCurrentWeatherResponse(): Single<CurrentWeatherResponse> =
        Single.just(
            createSampleCurrentWeatherResponse().withStatusCode(
                HttpURLConnection.HTTP_OK.toString()
            )
        )


    fun generateFiveDayForecast(): Single<FiveDayResponse> =
        Single.just(
            createSampleForecastResponse().withStatusCode(
                HttpURLConnection.HTTP_OK.toString()
            )
        )
}