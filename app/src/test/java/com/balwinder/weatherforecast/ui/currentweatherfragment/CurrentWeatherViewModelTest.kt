package com.balwinder.weatherforecast.ui.currentweatherfragment

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.weather.core.domain.usecase.CurrentLocationWeatherUseCase
import com.weather.core.domain.usecase.CurrentWeatherUseCase
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class CurrentWeatherViewModelTest {

    lateinit var currentWeatherViewModel: CurrentWeatherViewModel

    lateinit var  CurrentWeatherNavigator: CurrentWeatherNavigator

     lateinit var  currentWeatherUseCase: CurrentWeatherUseCase
     lateinit var  currentLocationWeatherUseCase: CurrentLocationWeatherUseCase


    @Before
   fun setup()
    {
        currentWeatherUseCase = mock()
        currentLocationWeatherUseCase = mock()
        CurrentWeatherNavigator = mock()
        currentWeatherViewModel = CurrentWeatherViewModel(currentWeatherUseCase,currentLocationWeatherUseCase)
        currentWeatherViewModel.setNavigator(CurrentWeatherNavigator)

    }


    @Test
    fun getCurrentList() {
    }

    @Test
    fun loadCurrentWeather() {

        val city = "dubai,abu dhabi,london,new york"
        currentWeatherViewModel.loadCurrentWeather(city)

    }



    @Test
    fun loadCurrenLocationtWeather() {
    }
}