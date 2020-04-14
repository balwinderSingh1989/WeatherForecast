package com.weather.core.domain.usecase

import com.weather.core.data.repository.forecastWeather.model.FiveDayResponse
import com.weather.core.data.repository.packages.ForecastWeatherRepository
import com.weather.core.domain.SingleUseCase
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class ForecastWeatherUseCase @Inject constructor(
    private val forecastWeatherRepository: ForecastWeatherRepository
) :
    SingleUseCase<FiveDayResponse, ForecastWeatherUseCase.Params>() {

    override fun buildUseCase(params: Params?): Single<FiveDayResponse> {
        return with(params!!)
        {
            forecastWeatherRepository.getFiveDaysForecast(
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