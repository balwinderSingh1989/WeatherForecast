package com.balwinder.weatherforecast.ui.main

import com.balwinder.weatherforecast.di.module.ActivityModule
import com.balwinder.weatherforecast.ui.currentweatherfragment.CurrentWeatherFragment
import com.balwinder.weatherforecast.ui.forecast.FiveDaysForecastFragment
import com.balwinder.weatherforecast.ui.hourlyForecast.HourlyForecastFragment
import com.weather.core.di.scope.PerActivity
import com.weather.core.di.scope.PerFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ActivityModule::class])
abstract class MainActivityModule {

    @PerActivity
    @Binds
    abstract fun bindMainActivty(mainActivity: MainActivity): MainActivity

    @PerFragment
    @ContributesAndroidInjector
    abstract fun providesEidaScanFragmet(): CurrentWeatherFragment


    @PerFragment
    @ContributesAndroidInjector
    abstract fun providesForecastFragment(): FiveDaysForecastFragment


    @PerFragment
    @ContributesAndroidInjector
    abstract fun providesHourlyForecastFragment(): HourlyForecastFragment


}