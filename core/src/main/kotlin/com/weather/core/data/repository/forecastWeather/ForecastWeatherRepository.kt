package com.weather.core.data.repository.packages

import com.weather.core.data.repository.DataRemoteConfig
import com.weather.core.data.repository.factory.DataFactory
import com.weather.core.data.repository.forecastWeather.model.FiveDayResponse
import com.weather.core.data.repository.remote.APIservice
import com.weather.core.domain.executor.ScheduleProvider
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class ForecastWeatherRepository @Inject constructor(
    val APIservice: APIservice,
    val scheduleProvider: ScheduleProvider
) {

    @Inject
    lateinit var dataRemoteConfig: DataRemoteConfig


    fun getFiveDaysForecast(
        lat: String,
        lon: String,
        appId: String,
        unit : String
    ): Single<FiveDayResponse> {
        if(dataRemoteConfig.isMock)
        {

            return  DataFactory.generateFiveDayForecast()
        }
        else
        return APIservice.getFiveDaysWeather(
           lat,lon,unit,"en",appId
        )
    }
}