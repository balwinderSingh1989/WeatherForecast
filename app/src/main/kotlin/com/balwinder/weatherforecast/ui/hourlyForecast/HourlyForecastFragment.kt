package com.balwinder.weatherforecast.ui.hourlyForecast

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.balwinder.weatherforecast.BR
import com.balwinder.weatherforecast.R
import com.balwinder.weatherforecast.base.BaseFragment
import com.balwinder.weatherforecast.databinding.HourlyWeatherFragmentBinding
import com.balwinder.weatherforecast.ui.forecast.HourlyNavigator
import com.balwinder.weatherforecast.ui.hourlyForecast.adapter.HourlyForecastWeatherAdapter
import com.balwinder.weatherforecast.util.DialogUtils
import com.weather.core.data.repository.forecastWeather.model.ItemHourly
import kotlinx.android.synthetic.main.hourly_weather_fragment.*


class HourlyForecastFragment :
    BaseFragment<HourlyWeatherFragmentBinding, HourlyForecastViewModel>(),
    HourlyNavigator {

    override val bindingVariable = BR.viewModel

    override val viewModel = HourlyForecastViewModel::class.java

    override fun getLayoutId() = R.layout.hourly_weather_fragment

    private lateinit var hourlyForecastWeatherAdapter: HourlyForecastWeatherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectedViewModel.setNavigator(this)

    }

    override fun initUserInterface(rootView: View) {

        setupSharedTransition()

        initHourlyAdapter()


        val args: HourlyForecastFragmentArgs by navArgs()
        var arraylistItemsHourly = args.listHours.let { initList ->
            ArrayList<ItemHourly>(initList.size).apply {
                initList.forEach { add(it) }
            }
        }
        hourlyForecastWeatherAdapter.setItems(arraylistItemsHourly)
        //first element as current day weather
        injectedViewModel.dayWeatherItem.set(arraylistItemsHourly[0])

        fabClose.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    private fun initHourlyAdapter() {
        hourlyForecastWeatherAdapter = HourlyForecastWeatherAdapter(context!!)
        recyclerViewHourOfDay.apply {
            adapter = hourlyForecastWeatherAdapter
        }
    }

    private fun setupSharedTransition() {
        val inflateTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = inflateTransition
    }


    override fun showAlert(stringId: Int, message: String) {
        DialogUtils.showInfoDialog(context!!, "Error", message)
    }


}