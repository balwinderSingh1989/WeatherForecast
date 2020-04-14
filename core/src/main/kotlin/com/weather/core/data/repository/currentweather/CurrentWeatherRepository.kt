package com.weather.core.data.repository.currentweather

import com.weather.core.data.repository.DataRemoteConfig
import com.weather.core.data.repository.currentweather.model.CurrentWeatherResponse
import com.weather.core.data.repository.factory.DataFactory
import com.weather.core.data.repository.remote.APIservice
import com.weather.core.domain.executor.ScheduleProvider
import io.reactivex.Single
import javax.inject.Inject

class CurrentWeatherRepository @Inject constructor(
    val APIservice: APIservice,
    val scheduleProvider: ScheduleProvider
) {

    @Inject
    lateinit var dataRemoteConfig: DataRemoteConfig


    fun getCurrentWeather(
        city: String,
        appId: String,
        unit : String
    ): Single<CurrentWeatherResponse> {

        if(dataRemoteConfig.isMock)
        {
         return  DataFactory.generateCurrentWeatherResponse()

        }
        else {
            return APIservice.getCurrentWeather(
                city, appId, unit
            )
        }
    }


    fun getCurrentWeatherByLocation(
        lat: String,
        lon: String,
        appId: String,
        unit : String
    ): Single<CurrentWeatherResponse> {

        if(dataRemoteConfig.isMock)
        {
            return  DataFactory.generateCurrentWeatherResponse()
        }
        return APIservice.getCurrentWeatherByGeo(
            lat,lon,appId,unit
        )
    }
}