package com.balwinder.weatherforecast.base

import android.view.View

interface ItemLongClickListener {
    fun onItemLongClicked(position: Int, view: View)
}