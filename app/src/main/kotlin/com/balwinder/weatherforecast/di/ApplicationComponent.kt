package com.balwinder.weatherforecast.di

import android.content.Context
import com.balwinder.weatherforecast.WeatherApp
import com.balwinder.weatherforecast.di.module.ActivityBindingModule
import com.balwinder.weatherforecast.di.module.AppModule
import com.balwinder.weatherforecast.di.module.ViewModelModule
import com.weather.core.di.scope.PerApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule


@PerApplication
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        NetworkModule::class,
        ActivityBindingModule::class,
        ViewModelModule::class,
        AppModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<WeatherApp> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }


}

