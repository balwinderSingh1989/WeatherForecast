package com.weather.core.data.repository.currentweather

import com.nhaarman.mockito_kotlin.mock
import com.weather.core.data.repository.remote.APIservice
import com.weather.core.domain.executor.AppSchedulerProvider
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CurrentWeatherRepositoryTest {

    lateinit var apIservice: APIservice
    lateinit var schedulerProvider: AppSchedulerProvider
    lateinit var currentWeatherRepository: CurrentWeatherRepository

    @Before
    fun setUp() {
        apIservice = mock()
        schedulerProvider = mock()
        currentWeatherRepository = CurrentWeatherRepository(apIservice,schedulerProvider)
    }





    @After
    fun tearDown() {
    }
}