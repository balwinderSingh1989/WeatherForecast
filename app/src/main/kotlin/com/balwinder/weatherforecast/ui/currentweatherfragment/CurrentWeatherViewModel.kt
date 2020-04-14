package com.balwinder.weatherforecast.ui.currentweatherfragment

import androidx.lifecycle.MutableLiveData
import com.balwinder.weatherforecast.R
import com.balwinder.weatherforecast.base.BaseViewModel
import com.weather.core.data.repository.currentweather.model.CurrentWeatherResponse
import com.weather.core.domain.remote.Status
import com.weather.core.domain.rxcallback.CallbackWrapper
import com.weather.core.domain.usecase.CurrentLocationWeatherUseCase
import com.weather.core.domain.usecase.CurrentWeatherUseCase
import com.weather.core.utility.constants.default_api_id
import com.weather.core.utility.logger.AppLogger
import retrofit2.Response
import java.util.regex.Pattern
import javax.inject.Inject


class CurrentWeatherViewModel @Inject constructor(
    private val currentWeatherUseCase: CurrentWeatherUseCase,
    private val currentLocationWeatherUseCase: CurrentLocationWeatherUseCase
) : BaseViewModel<CurrentWeatherNavigator>() {

    var currentList: MutableList<CurrentWeatherResponse> = ArrayList()
    var currentWeatherLiveData = MutableLiveData<List<CurrentWeatherResponse>>()


    /**
     * loads current weather for  comma seperate
     * multiple cities.
     *Validate the cities name and load from repository
     */
    fun loadCurrentWeather(city: String) {

        var citiesArray = city.split(",") as MutableList<String>
        var isValidQuery = false
        //Regex for cities name in searchable view
        val regex = "^[a-zA-Z0-9 ]+$"
        val pattern= Pattern.compile(regex)

        for (city in citiesArray) {

            if (city.isNotEmpty()) {
                var city = city.trim()
                if (pattern.matcher(city).matches()) {
                    isValidQuery = true
                    city.trim()
                    addDisposable(
                        currentWeatherUseCase.execute(
                            CurrnetWeatherResponseCallback(),
                            CurrentWeatherUseCase.Params.create(
                                city = city,
                                appID = default_api_id,
                                unit = "metric"
                            )
                        )
                    )
                }
            }
        }
        if (isValidQuery) {
            getNavigator()!!.showLoading("Fetching weather...")
        }

    }

    fun loadCurrenLocationtWeather(lat: String, lon: String) {

        getNavigator()!!.showLoading("Fetching current weather...")

        addDisposable(
            currentLocationWeatherUseCase.execute(
                CurrnetWeatherResponseCallback(),
                CurrentLocationWeatherUseCase.Params.create(
                    lat = lat,
                    lon = lon,
                    appID = default_api_id,
                    unit = "metric"
                )
            )
        )
    }


    inner class CurrnetWeatherResponseCallback :
        CallbackWrapper<CurrentWeatherResponse>() {

        override fun onEmptyResponse() {
        }

        override fun onResponseSuccess(response: CurrentWeatherResponse) {


            getNavigator()!!.hideLoading()

            //   var currentWeatherResponse: CurrentWeatherResponse


            currentList.add(response)

            currentWeatherLiveData.postValue(currentList)


        }

        override fun onResponseError(status: Status) {
            getNavigator()!!.hideLoading()
        }

        override fun onNetworkError(throwable: Throwable) {
            getNavigator()!!.hideLoading()
            getNavigator()!!
                .showAlert(
                    R.string.error,
                    getResourceProvider()
                        .getString(
                            R.string.error_failed_to_connect
                        )
                )
        }

        override fun onServerError(throwable: Throwable) {
            getNavigator()!!.hideLoading()

        }
    }

}
