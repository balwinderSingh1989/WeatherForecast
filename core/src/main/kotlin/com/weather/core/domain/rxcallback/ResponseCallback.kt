package com.weather.core.domain.rxcallback

import com.weather.core.domain.remote.Status

interface ResponseCallback<T> {

    fun onResponseSuccess(response: T)

    fun onResponseError(status: Status)

    fun onNetworkError(throwable: Throwable)

    fun onServerError(throwable: Throwable)

    // TODO - Remove after empty body added into the responsess
    fun onEmptyResponse()

}