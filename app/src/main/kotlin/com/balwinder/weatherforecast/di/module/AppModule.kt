package com.balwinder.weatherforecast.di.module

import com.balwinder.weatherforecast.BuildConfig
import com.weather.core.data.cache.AppDataManager
import com.weather.core.data.cache.DataManager
import com.weather.core.data.cache.pref.PrefManager
import com.weather.core.data.cache.pref.PreferenceHelper
import com.weather.core.data.repository.DataRemoteConfig
import com.weather.core.di.scope.PerApplication
import com.weather.core.domain.executor.AppSchedulerProvider
import com.weather.core.domain.executor.ScheduleProvider
import com.weather.core.domain.resourceProvider.ResourceProvider
import com.weather.core.domain.resourceProvider.ResourceProviderHelper
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class AppModule {


    @Module
    companion object {

        @PerApplication
        @Provides
        @JvmStatic
        fun provideConfig() =
            DataRemoteConfig(
                isMock = BuildConfig.FLAVOR.contains("Mock")
            )

        @PerApplication
        @Provides
        @JvmStatic
        fun providesSchedulerProvider(): ScheduleProvider {
            return AppSchedulerProvider()
        }

    }

    @Binds
    @PerApplication
    abstract fun bindResourceProvider(resourceProviderHelper: ResourceProvider): ResourceProviderHelper

    @Binds
    @PerApplication
    abstract fun bindPrefManager(prefManager: PrefManager): PreferenceHelper


    @Binds
    @PerApplication
    abstract fun bindAppDataManager(appDataManager: AppDataManager): DataManager


}