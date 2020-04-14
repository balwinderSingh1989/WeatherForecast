package com.balwinder.weatherforecast.di.module

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentManager
import com.balwinder.weatherforecast.base.BaseActivity
import com.balwinder.weatherforecast.base.BaseViewModel
import com.weather.core.di.qualifier.ActivityContext
import com.weather.core.di.scope.PerActivity
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.support.DaggerAppCompatActivity

@Module
abstract class ActivityModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @PerActivity
        fun provideFragmentManager(activity: AppCompatActivity): FragmentManager {
            return activity.supportFragmentManager
        }

    }

    @Binds
    @PerActivity
    abstract fun bindAppCompatActivity(activity: BaseActivity<ViewDataBinding, BaseViewModel<*>>): DaggerAppCompatActivity

    @Binds
    @PerActivity
    abstract fun bindActivity(activity: DaggerAppCompatActivity): Activity

    @ActivityContext
    @Binds
    @PerActivity
    abstract fun bindActivityContext(activity: Activity): Context

}