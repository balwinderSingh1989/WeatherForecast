package com.balwinder.weatherforecast.di.module

import com.balwinder.weatherforecast.di.ViewModelBuilder
import com.balwinder.weatherforecast.ui.main.MainActivity
import com.balwinder.weatherforecast.ui.main.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {


    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class, MainActivityModule::class
        ]
    )
    internal abstract fun bindMainactivity(): MainActivity


}

