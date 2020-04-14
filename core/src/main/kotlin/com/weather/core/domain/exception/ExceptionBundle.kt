package com.weather.core.domain.exception

interface ExceptionBundle {
    fun getException(): Exception?
    fun getMessage(): String
}