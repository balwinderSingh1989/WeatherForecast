package com.balwinder.weatherforecast.ui.currentweatherfragment.adapter

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import com.balwinder.weatherforecast.R
import com.balwinder.weatherforecast.base.BaseRecyclerViewAdapter
import com.balwinder.weatherforecast.base.BaseVH
import com.balwinder.weatherforecast.base.ItemClickListener
import com.balwinder.weatherforecast.databinding.CurrentWeatherItemBinding
import com.balwinder.weatherforecast.util.setWeatherIcon
import com.balwinder.weatherforecast.util.viewUtils.TextViewFactory
import com.weather.core.data.repository.currentweather.model.CurrentWeatherResponse
import com.weather.core.utility.constants.getWeatherStatus
import java.util.*


class CurrentWeatherAdapter(itemClickListener: ItemClickListener?, private val context: Context) :
    BaseRecyclerViewAdapter<CurrentWeatherResponse>(itemClickListener) {


    override fun createBaseViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseVH<CurrentWeatherResponse> {

        var binding: CurrentWeatherItemBinding
        binding = CurrentWeatherItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )


        setupTextSwitchers(binding)
        return WeatherListHolder(
            binding
        )

    }


    private fun setupTextSwitchers(binding: CurrentWeatherItemBinding) {
        var typeface = Typeface.createFromAsset(context.assets, "fonts/Vazir.ttf")



        binding.tempTextView.setFactory(
            TextViewFactory(
                context,
                R.style.TempTextView,
                true,
                typeface
            )
        )
        binding.tempTextView.setInAnimation(context, R.anim.slide_in_right)
        binding.tempTextView.setOutAnimation(context, R.anim.slide_out_left)
        binding.descriptionTextView.setFactory(
            TextViewFactory(
                context,
                R.style.DescriptionTextView,
                true,
                typeface
            )
        )


        binding.descriptionTextView.setInAnimation(context, R.anim.slide_in_right)
        binding.descriptionTextView.setOutAnimation(context, R.anim.slide_out_left)
        binding.humidityTextView.setFactory(
            TextViewFactory(
                context,
                R.style.HumidityTextView,
                false,
                typeface
            )
        )
        binding.humidityTextView.setInAnimation(context, R.anim.slide_in_bottom)
        binding.humidityTextView.setOutAnimation(context, R.anim.slide_out_top)
        binding.windTextView.setFactory(
            TextViewFactory(
                context,
                R.style.WindSpeedTextView,
                false,
                typeface
            )
        )
        binding.windTextView.setInAnimation(context, R.anim.slide_in_bottom)
        binding.windTextView.setOutAnimation(context, R.anim.slide_out_top)
    }


    inner class WeatherListHolder(private val binding: CurrentWeatherItemBinding) :
        BaseVH<CurrentWeatherResponse>(binding.root) {

        var typeface = Typeface.createFromAsset(context.assets, "fonts/Vazir.ttf")


        override fun bindItem(item: CurrentWeatherResponse) {
            binding.item = item
            binding.executePendingBindings()

            binding.tempTextView.setText(
                String.format(
                    Locale.getDefault(),
                    "%.0f°", item.main!!.temp
                )
            )
            binding.windTextView.setText(
                String.format(
                    Locale.getDefault(),
                    context.resources.getString(R.string.wind_unit_label),
                    item.wind!!.speed
                )
            )
            binding.humidityTextView.setText(
                String.format(
                    Locale.getDefault(),
                    "%d%%",
                    item.main!!.humidity
                )
            )

            binding.descriptionTextView.setText(
                getWeatherStatus(
                    item.weather!!.get(0).id
                )
            )

            setWeatherIcon(
                context, binding.imageView,
                item.weather!!.get(0).id
            )

        }


    }


}