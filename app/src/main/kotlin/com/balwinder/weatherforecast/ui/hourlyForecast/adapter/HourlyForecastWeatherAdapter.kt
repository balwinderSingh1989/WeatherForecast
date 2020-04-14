package com.balwinder.weatherforecast.ui.hourlyForecast.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.balwinder.weatherforecast.base.BaseRecyclerViewAdapter
import com.balwinder.weatherforecast.base.BaseVH
import com.balwinder.weatherforecast.databinding.ItemWeatherHourOfDayBinding
import com.weather.core.data.repository.forecastWeather.model.ItemHourly


class HourlyForecastWeatherAdapter(private val context: Context) :
    BaseRecyclerViewAdapter<ItemHourly>() {


    override fun createBaseViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseVH<ItemHourly> {

        return ForecastListHolder(
            ItemWeatherHourOfDayBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }


    inner class ForecastListHolder(private val binding: ItemWeatherHourOfDayBinding) :
        BaseVH<ItemHourly>(binding.root) {


        override fun bindItem(item: ItemHourly) {
            binding.item = item
            binding.executePendingBindings()


        }
    }


}