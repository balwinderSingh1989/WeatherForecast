package com.weather.core.domain.usecase

import com.weather.core.data.repository.currentweather.CurrentWeatherRepository
import com.weather.core.data.repository.currentweather.model.CurrentWeatherResponse
import com.weather.core.domain.SingleUseCase
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class CurrentWeatherUseCase @Inject constructor(
    private val currentWeatherRepository: CurrentWeatherRepository
) :
    SingleUseCase<CurrentWeatherResponse, CurrentWeatherUseCase.Params>() {

    override fun buildUseCase(params: Params?): Single<CurrentWeatherResponse> {
        return with(params!!)
        {
            currentWeatherRepository.getCurrentWeather(
                city = city,
                appId = appID,
                unit = unit
            )
        }
    }
    data class Params constructor(
        val city: String,
        val appID: String,
        val unit : String
    ) {
        companion object {
            fun create(
                city: String,
                appID: String,
                unit : String
            ) = Params(city, appID ,unit )
        }
    }





}