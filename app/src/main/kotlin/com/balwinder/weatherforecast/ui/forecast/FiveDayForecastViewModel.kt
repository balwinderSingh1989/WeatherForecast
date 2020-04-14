package com.balwinder.weatherforecast.ui.forecast

import android.location.Location
import androidx.databinding.ObservableField
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.balwinder.weatherforecast.R
import com.balwinder.weatherforecast.base.BaseViewModel
import com.balwinder.weatherforecast.util.getWeatherIcon
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.weather.core.data.repository.forecastWeather.model.FiveDayResponse
import com.weather.core.data.repository.forecastWeather.model.ItemHourly
import com.weather.core.domain.remote.Status
import com.weather.core.domain.rxcallback.CallbackWrapper
import com.weather.core.domain.usecase.ForecastWeatherUseCase
import com.weather.core.utility.constants.default_api_id
import com.weather.core.utility.extensions.isConnected
import retrofit2.Response
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class FiveDayForecastViewModel @Inject constructor(
    private val forecastWeatherUseCase: ForecastWeatherUseCase
) : BaseViewModel<ForecastNavigator>(), LifecycleObserver {


    var forecastWeatherLiveData = MutableLiveData<ArrayList<MutableList<ItemHourly>>>()
    var locaitonLiveData = MutableLiveData<Location>()
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    var city = ObservableField<String>()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun initLocation() {
        fusedLocationClient =
            LocationServices.getFusedLocationProviderClient(getResourceProvider().getContext())
    }

    /**
     *
     * load five days / 3hours data
     */
    fun loadFiveDayForecast(lat: String, lon: String) {

        if (getResourceProvider().getContext().isConnected()) {
            getNavigator()!!.showLoading("Fetching weather for next 5 days...")
            addDisposable(
                forecastWeatherUseCase.execute(
                    ForecastWeatherResponseCallback(),
                    ForecastWeatherUseCase.Params.create(
                        lat = lat,
                        lon = lon,
                        appID = default_api_id,
                        unit = "metric"
                    )
                )
            )
        } else {
            //show snack bar here
            getNavigator()!!
                .showAlert(
                    R.string.error,
                    getResourceProvider()
                        .getString(
                            R.string.no_internet_message
                        )
                )

        }
    }


    inner class ForecastWeatherResponseCallback :
        CallbackWrapper<FiveDayResponse>() {
        override fun onEmptyResponse() {
        }

        override fun onResponseSuccess(response: FiveDayResponse) {
            getNavigator()!!.hideLoading()
            if (response.cod.equals("200")) {
                city.set(response.city!!.name)
                val dayData = getFiveDaysClubbedData(response)
                forecastWeatherLiveData.postValue(dayData)
            }
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


    /**
     * club weather for days together and return sorted
     * array.
     *
     */
    private fun getFiveDaysClubbedData(response: FiveDayResponse)
            : ArrayList<MutableList<ItemHourly>> {

        val weatherDaysHashmap = HashMap<String, MutableList<ItemHourly>>()
        val fiveDaysWeather: ArrayList<MutableList<ItemHourly>> = arrayListOf()

        val listItemHourlies = ArrayList(response.list)
        for (itemHourly in listItemHourlies) {
            val calendar = Calendar.getInstance(TimeZone.getDefault())
            calendar.timeInMillis = itemHourly.dt * 1000L
            //gorup here with same date

            val date = itemHourly.dtTxt!!.split(" ")[0]

            if (!weatherDaysHashmap.containsKey(date)) {

                val itemhour = arrayListOf<ItemHourly>()

                itemHourly.weatherIcon = getWeatherIcon(itemHourly.weather!!.get(0).id)

                itemhour.add(itemHourly)
                weatherDaysHashmap.put(date, itemhour)
            } else {
                itemHourly.weatherIcon = getWeatherIcon(itemHourly.weather!!.get(0).id)
                weatherDaysHashmap[date]!!.add(itemHourly)
            }
        }

        val sortedHashmap = weatherDaysHashmap.toSortedMap(compareBy { it })
        for ((key, value) in sortedHashmap) {
            sortedHashmap[key]?.let {
                fiveDaysWeather.add(it)
            }
        }
        return fiveDaysWeather
    }


    /**
     * get last known location from fusedLocationClient
     * return last saved location at shared pref iff locaiton services is off or disabled
     */

    fun getLastLocation() {

        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            if (location == null) {
                /**
                 * if location is null (due to disabled location services),
                 * then try to access from shared pref
                 */

                if (getDataManager().getLastSavedLat().isNotEmpty()) {


                    var location = Location("")
                    location.latitude = getDataManager().getLastSavedLat().toDouble()
                    location.longitude = getDataManager().getLastSavedLon().toDouble()
                    locaitonLiveData.postValue(
                        location
                    )
                } else {

                    /**
                     * no location in shared pref,
                     * throw an error
                     *
                     */
                    getNavigator()!!.showAlert(
                        R.string.error,
                        getResourceProvider().getContext().getString(R.string.failed_fetch_location)
                    )
                }
            } else {
                //always save the last known locaiton
                getDataManager().updateLastKnownLocation(
                    location.latitude.toString(),
                    location.longitude.toString()
                )
                //use this location to get weather details
                locaitonLiveData.postValue(location)
            }

        }

    }

}




