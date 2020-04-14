package com.weather.core.domain.remote

import com.google.gson.annotations.SerializedName

data class Error(
    //TODO - add two different error codes i.e. for server and local error
    @SerializedName("message") val message: String,
    @SerializedName("code") val code: String
)