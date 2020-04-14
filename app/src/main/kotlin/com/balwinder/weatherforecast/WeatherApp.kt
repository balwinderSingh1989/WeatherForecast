package com.balwinder.weatherforecast

import androidx.appcompat.app.AppCompatDelegate
import com.balwinder.weatherforecast.di.ApplicationComponent
import com.balwinder.weatherforecast.di.DaggerApplicationComponent
import com.weather.core.utility.logger.AppLogger
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class WeatherApp : DaggerApplication() {


    companion object {
        lateinit var appComponent: ApplicationComponent
        var TAG: String = WeatherApp::class.java.name
    }


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        appComponent = DaggerApplicationComponent.factory().create(applicationContext)
        return appComponent
    }

    override fun onCreate() {
        super.onCreate()

        AppLogger.d(TAG, "app initiated")

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

    }


}
