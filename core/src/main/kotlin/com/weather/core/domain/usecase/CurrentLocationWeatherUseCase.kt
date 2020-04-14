package com.weather.core.domain.usecase

import com.weather.core.data.repository.currentweather.CurrentWeatherRepository
import com.weather.core.data.repository.currentweather.model.CurrentWeatherResponse
import com.weather.core.domain.SingleUseCase
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class CurrentLocationWeatherUseCase @Inject constructor(
    private val currentWeatherRepository: CurrentWeatherRepository
) :
    SingleUseCase<CurrentWeatherResponse, CurrentLocationWeatherUseCase.Params>() {

    override fun buildUseCase(params: Params?): Single<CurrentWeatherResponse> {
        return with(params!!)
        {
            currentWeatherRepository.getCurrentWeatherByLocation(
                lat = lat,
                lon = lon,
                appId = appID,
                unit = unit
            )
        }
    }
    data class Params constructor(
        val lat: String,
        val lon: String,
        val appID: String,
        val unit : String
    ) {
        companion object {
            fun create(
                lat: String,
                lon: String,
                appID: String,
                unit : String
            ) = Params(lat,lon, appID ,unit )
        }
    }





}