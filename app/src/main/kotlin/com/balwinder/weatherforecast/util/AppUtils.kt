package com.balwinder.weatherforecast.util

import android.content.Context
import android.widget.ImageView
import com.balwinder.weatherforecast.R


/**
 * Set icon to imageView according to weather code status
 *
 * @param context     instance of [Context]
 * @param imageView   instance of [android.widget.ImageView]
 * @param weatherCode code of weather status
 */
fun setWeatherIcon(context: Context, imageView: ImageView, weatherCode: Int) {
    when {
        weatherCode / 100 == 2 -> imageView.setImageDrawable(context.getDrawable(R.drawable.ic_storm_weather))
        weatherCode / 100 == 3 -> imageView.setImageDrawable(context.getDrawable(R.drawable.ic_rainy_weather))
        weatherCode / 100 == 5 -> imageView.setImageDrawable(context.getDrawable(R.drawable.ic_rainy_weather))
        weatherCode / 100 == 6 -> imageView.setImageDrawable(context.getDrawable(R.drawable.ic_snow_weather))
        weatherCode / 100 == 7 -> imageView.setImageDrawable(context.getDrawable(R.drawable.ic_unknown))
        weatherCode == 800 -> imageView.setImageDrawable(context.getDrawable(R.drawable.ic_clear_day))
        weatherCode == 801 -> imageView.setImageDrawable(context.getDrawable(R.drawable.ic_few_clouds))
        weatherCode == 803 -> imageView.setImageDrawable(context.getDrawable(R.drawable.ic_broken_clouds))
        weatherCode / 100 == 8 -> imageView.setImageDrawable(context.getDrawable(R.drawable.ic_cloudy_weather))
    }
}


fun getWeatherIcon(weatherCode: Int): Int {
    return when {
        weatherCode / 100 == 2 -> R.drawable.ic_storm_weather
        weatherCode / 100 == 3 -> R.drawable.ic_rainy_weather
        weatherCode / 100 == 5 -> R.drawable.ic_rainy_weather
        weatherCode / 100 == 6 -> R.drawable.ic_snow_weather
        weatherCode / 100 == 7 -> R.drawable.ic_unknown
        weatherCode == 800 -> R.drawable.ic_clear_day
        weatherCode == 801 -> R.drawable.ic_few_clouds
        weatherCode == 803 -> R.drawable.ic_broken_clouds
        weatherCode / 100 == 8 -> R.drawable.ic_cloudy_weather
        else -> R.drawable.ic_unknown
    }
}


