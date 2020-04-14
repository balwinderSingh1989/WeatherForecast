package com.weather.core.utility

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {


    fun getCurrentTimeStamp(): String {

        val longTimeStamp = System.currentTimeMillis()
        return longTimeStamp.toString()
    }

    fun getCurrentDate(): String {
        val date = Date()
        val strDateFormat = "yyyy MM dd"
        val dateFormat = SimpleDateFormat(strDateFormat)
        return dateFormat.format(date)
    }



}