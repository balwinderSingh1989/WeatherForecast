package com.weather.core.data.repository.remote

import com.weather.core.data.repository.currentweather.model.CurrentWeatherResponse
import com.weather.core.data.repository.forecastWeather.model.FiveDayResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface APIservice {


    /**
     * Get five days weather forecast.
     *
     * @param q     String name of city
     * @param units String units of response
     * @param lang  String language of response
     * @param appId String api key
     * @return instance of [FiveDayResponse]
     */
    @GET("forecast")
     fun getFiveDaysWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("units") units: String,
        @Query("lang") lang: String,
        @Query("appid") appId: String
    ): Single<FiveDayResponse>


    /**
     * Get current weather of city
     *
     * @param q     String name of city
     * @param units String units of response
     * @param lang  String language of response
     * @param appId String api key
     * @return instance of [CurrentWeatherResponse]
     */
    @GET("weather")
    fun getCurrentWeather(
        @Query("q") q: String,
        @Query("appid") appId: String,
        @Query("units")  units : String
    ): Single<CurrentWeatherResponse>


    /**
     * Get current weather of city
     *
     * @param q     String name of city
     * @param units String units of response
     * @param lang  String language of response
     * @param appId String api key
     * @return instance of [CurrentWeatherResponse]
     */
    @GET("weather")
    fun getCurrentWeatherByGeo(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appId: String,
        @Query("units")  units : String
    ): Single< CurrentWeatherResponse>



}