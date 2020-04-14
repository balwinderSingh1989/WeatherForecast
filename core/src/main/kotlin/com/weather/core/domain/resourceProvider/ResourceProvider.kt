package com.weather.core.domain.resourceProvider

import android.content.Context
import com.weather.core.di.scope.PerApplication
import javax.inject.Inject

@PerApplication
class ResourceProvider @Inject constructor(private val context: Context) : ResourceProviderHelper {


    override fun getPluginDirectoryPath(): String {
        return context.applicationInfo.nativeLibraryDir
    }

    override fun getString(resourceId: Int): String {
        return context.getString(resourceId)
    }

    override fun getString(resourceId: Int, str: String): String {
        return context.getString(resourceId, str)
    }

    override fun getContext(): Context {
        return context
    }


}
