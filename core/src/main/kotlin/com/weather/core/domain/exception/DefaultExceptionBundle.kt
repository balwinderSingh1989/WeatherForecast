package com.weather.core.domain.exception

class DefaultExceptionBundle(private val mException: Exception?) :
    ExceptionBundle {

    companion object {
        private const val DEFAULT_MESSAGE = "Unknown exception"
    }

    override fun getException(): Exception? {
        return mException
    }

    override fun getMessage(): String {

        mException?.let {
            return it.message!!
        }

        return DEFAULT_MESSAGE
    }
}
