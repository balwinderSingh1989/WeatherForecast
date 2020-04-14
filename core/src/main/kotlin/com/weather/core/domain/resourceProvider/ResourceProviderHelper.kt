package com.weather.core.domain.resourceProvider

import android.content.Context

interface ResourceProviderHelper {


    fun getPluginDirectoryPath(): String

    fun getContext(): Context

    fun getString(resourceId: Int): String

    fun getString(resourceId: Int, str: String): String
}
