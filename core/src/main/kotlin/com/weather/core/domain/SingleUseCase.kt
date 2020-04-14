package com.weather.core.domain

import com.google.gson.Gson
import com.weather.core.domain.remote.BaseError
import com.weather.core.domain.remote.BaseResponse
import com.weather.core.domain.remote.Status
import com.weather.core.domain.remote.exception.*
import com.weather.core.domain.rxcallback.CallbackWrapper
import com.weather.core.utility.logger.AppLogger
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import retrofit2.Response
import java.io.EOFException
import java.io.IOException
import java.net.HttpURLConnection
import java.util.concurrent.TimeoutException

abstract class SingleUseCase<T : BaseResponse, Params> {

    companion object {
        const val TAG = "SingleUseCase"
    }

    private var threadScheduler: Scheduler = Schedulers.io()
    private var postExecutionThread: Scheduler = AndroidSchedulers.mainThread()

    fun execute(
        callbackWrapper: CallbackWrapper<T>?,
        params: Params?, threadScheduler: Scheduler, postExecutionThread: Scheduler
    ) {
        this.threadScheduler = threadScheduler
        this.postExecutionThread = postExecutionThread

        execute(callbackWrapper, params)
    }


    abstract fun buildUseCase(params: Params?): Single<T>


    fun execute(
        callbackWrapper: CallbackWrapper<T>?,
        params: Params? = null
    ): Disposable? {
        if (callbackWrapper == null) {
            return null
        }

        val single = buildUseCase(params)
            .subscribeOn(threadScheduler)
            .observeOn(postExecutionThread)


        return single.subscribe({ result ->


           // var isSuccessWithWrror = false
//            result.errorBody()?.let {
//
//                var errorText = it.string()
//
//                try {
//                    errorText.let {
//                        AppLogger.e(TAG + " onResponseSuccess error", errorText)
//                    }
//
//                } catch (e: Exception) {
//                     errorText = "Invalid server response!"
//                }
//
//                isSuccessWithWrror = true
//                handleResponseError(
//                    errorText = errorText,
//                    callbackWrapper = callbackWrapper
//                )
//
//            }
//            if (!isSuccessWithWrror)
                callbackWrapper.onResponseSuccess(result)


        }, { exception ->


            when (exception) {

                is HttpException -> {

                    AppLogger.e(
                        TAG +
                                " exceptionCode",
                        exception.code().toString()
                    )

                    exception.response().errorBody()?.let {

                        val errorText = it.string()

                        AppLogger.e(TAG + " onRetrofitException", errorText)

                        handleResponseError(
                            errorText = errorText,
                            callbackWrapper = callbackWrapper
                        )

                    }
                }

                is SessionExpireException -> {
                    //TODO we should keep here empty, it has been handled in sessionManager already
                }

                is EOFException -> {
                    callbackWrapper.onEmptyResponse()
                }
                is ServerNotAvailableException -> {
                    callbackWrapper.onServerError(exception)
                }
                is HTTPNotFoundException -> {
                    callbackWrapper.onResponseError(
                        Status(
                            HttpURLConnection.HTTP_NOT_FOUND.toString(),
                            "No data found"
                        )
                    )
                }

                is IOException,
                is TimeoutException -> callbackWrapper.onNetworkError(
                    exception
                )

                is AuthorizationException -> {
                    AppLogger.e(
                        TAG,
                        "AUTH RESP=" + exception.message
                    )

                    handleResponseError(
                        errorText = exception.message!!,
                        callbackWrapper = callbackWrapper
                    )
                }

                is NoContentException -> {
                    callbackWrapper.onEmptyResponse()
                }

                is HTTPBadRequest -> {
                    callbackWrapper.onResponseError(
                        Status(
                            HttpURLConnection.HTTP_BAD_REQUEST.toString(),
                            "Something went wrong , please try again later"
                        )
                    )
                }

                else -> callbackWrapper.onServerError(exception)
            }

        })

    }

    private fun <T> handleResponseError(
        errorText: String,
        callbackWrapper: CallbackWrapper<T>
    ) {

        try {

            val baseError = Gson().fromJson(
                errorText,
                BaseError::class.java
            )


            if (baseError != null) {


                baseError.let { it ->

                    if (it.errors.isNullOrEmpty()) {


                        if (it.error == null) {
//                            if(it.message == null)
//                            {
//
//                                callbackWrapper.onResponseError(
//                                    Status(
//                                        code ="UNKNOWN",
//                                        message = "something went wrong!Please try again"
//                                    )
//                                )
//                            }
                            it.message.let {

                                callbackWrapper.onResponseError(
                                    Status(
                                        code = "UNKNOWN",
                                        message = it
                                    )
                                )
                            }

                        } else {

                            it.error.let { error ->

                                callbackWrapper.onResponseError(
                                    Status(
                                        code = error.code,
                                        message = error.message
                                    )
                                )

                            }
                        }

                    } else {

                        callbackWrapper.onResponseError(
                            Status(
                                code = it.errors[0].code,
                                message = it.errors[0].message
                            )
                        )
                    }

                }
            } else {
                callbackWrapper.onResponseError(
                    Status(
                        code = "CAN NOT DETERMINE THE ERROR",
                        message = "Something went wrong , please try again later"
                    )
                )
            }

        } catch (e: Exception) {
            e.printStackTrace()
            if (errorText.isNotEmpty()) {
                callbackWrapper.onResponseError(Status(message = errorText))
            } else
                callbackWrapper.onResponseError(Status(message = "Response error"))
        }

    }

}