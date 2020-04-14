package com.balwinder.weatherforecast.di.module

import androidx.lifecycle.ViewModel
import com.balwinder.weatherforecast.di.key.ViewModelKey
import com.balwinder.weatherforecast.ui.currentweatherfragment.CurrentWeatherViewModel
import com.balwinder.weatherforecast.ui.forecast.FiveDayForecastViewModel
import com.balwinder.weatherforecast.ui.hourlyForecast.HourlyForecastViewModel
import com.balwinder.weatherforecast.ui.main.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    fun bindMainViewModel(mainActivityViewModel: MainActivityViewModel)
            : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CurrentWeatherViewModel::class)
    fun bindEidaScanViewModel(currentWeatherViewModel: CurrentWeatherViewModel)
            : ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(FiveDayForecastViewModel::class)
    fun bindsForecastViewModel(fiveDayForecastViewModel: FiveDayForecastViewModel)
            : ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(HourlyForecastViewModel::class)
    fun bindsHourlyForecastViewModel(hourlyForecastViewModel: HourlyForecastViewModel)
            : ViewModel
}




