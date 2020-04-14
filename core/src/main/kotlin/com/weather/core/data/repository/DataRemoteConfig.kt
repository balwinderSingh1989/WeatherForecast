package com.weather.core.data.repository

import com.weather.core.di.scope.PerApplication
import javax.inject.Inject


@PerApplication
class DataRemoteConfig @Inject constructor(
    val isMock: Boolean
)