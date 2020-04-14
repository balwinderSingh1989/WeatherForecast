package com.weather.core.domain.remote

import com.google.gson.annotations.SerializedName

class BaseError(
    @SerializedName("errors") val errors: ArrayList<Error>?,
    @SerializedName("message") val message: String,
    @SerializedName("error") val error: Error?
)