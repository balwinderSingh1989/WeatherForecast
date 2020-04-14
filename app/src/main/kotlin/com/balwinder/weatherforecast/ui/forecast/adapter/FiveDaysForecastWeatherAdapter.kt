package com.balwinder.weatherforecast.ui.forecast.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.balwinder.weatherforecast.R
import com.balwinder.weatherforecast.base.BaseRecyclerViewAdapter
import com.balwinder.weatherforecast.base.BaseVH
import com.balwinder.weatherforecast.base.ItemClickListener
import com.balwinder.weatherforecast.databinding.DayWeatherBinding
import com.weather.core.data.repository.forecastWeather.model.ItemHourly


class FiveDaysForecastWeatherAdapter(

    private val itemClickListener: ItemClickListener?,
    private val context: Context,
    private val callBack: (ArrayList<ItemHourly>, View, View, View, View, View) -> Unit

) :
    BaseRecyclerViewAdapter<ArrayList<ItemHourly>>(itemClickListener) {


    override fun createBaseViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseVH<ArrayList<ItemHourly>> {

        var mBinding = DayWeatherBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )


        var viewHolder = ForecastListHolder(
            mBinding
        )

        viewHolder.setItemClickListener(null)
        mBinding.rootView.setOnClickListener {
            mBinding.item?.let {

                callBack.invoke(
                    getItems()[viewHolder.adapterPosition],
                    mBinding.cardView,
                    mBinding.weatherImageView,
                    mBinding.dayNameTextView,
                    mBinding.tempTextView,
                    mBinding.linearLayoutTempMaxMin
                )
            }
        }


        return viewHolder

    }


    inner class ForecastListHolder(private val binding: DayWeatherBinding) :
        BaseVH<ArrayList<ItemHourly>>(binding.root) {


        override fun bindItem(item: ArrayList<ItemHourly>) {

            val itemColor =
                containerView.resources.getIntArray(R.array.mdcolor_500)[adapterPosition]
            item.get(0).itemColor = itemColor

            if (item[0].main!!.tempMax < 0 && item[0].main!!.tempMax > -0.5) {
                item[0].main!!.tempMax = 0.0
            }
            if (item[0].main!!.tempMin < 0 && item[0].main!!.tempMin > -0.5) {
                item[0].main!!.tempMin = 0.0
            }
            if (item[0].main!!.temp < 0 && item[0].main!!.temp > -0.5) {
                item[0].main!!.temp = 0.0
            }

            binding.item = item[0]

            //set shadow color
            val colors = intArrayOf(
                Color.TRANSPARENT, context.resources.getIntArray(
                    R.array.mdcolor_500_alpha
                )[adapterPosition], Color.TRANSPARENT
            )
            val shape = GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, colors)
            shape.shape = GradientDrawable.OVAL
            binding.shadowView.background = shape

            binding.executePendingBindings()


        }
    }


}