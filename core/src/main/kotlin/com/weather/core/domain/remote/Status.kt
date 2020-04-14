package com.weather.core.domain.remote

import com.google.gson.annotations.SerializedName

class Status(
    @SerializedName("cod") val code: String? = "",
    @SerializedName("message") val message: String
) {

    companion object {
        fun withCode(code: String): Status {
            return Status(code, "No server messages obtained")
        }
    }

}