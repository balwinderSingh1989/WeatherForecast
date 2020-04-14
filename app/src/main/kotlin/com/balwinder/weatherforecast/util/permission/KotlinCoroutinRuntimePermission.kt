package com.balwinder.weatherforecast.util.permission

import androidx.fragment.app.Fragment
import com.github.florent37.runtimepermission.PermissionResult
import com.github.florent37.runtimepermission.RuntimePermission
import com.weather.core.domain.remote.exception.NoActivityException
import com.weather.core.domain.remote.exception.PermissionException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

suspend fun Fragment.askPermission(vararg permissions: String): PermissionResult =
    suspendCoroutine { continuation ->
        var resumed = false
        when (activity) {
            null -> continuation.resumeWithException(NoActivityException())
            else -> RuntimePermission.askPermission(this)
                .request(permissions.toList())
                .onResponse { result ->
                    if (!resumed) {
                        resumed = true
                        when {
                            result.isAccepted -> continuation.resume(result)
                            else -> continuation.resumeWithException(
                                PermissionException(result)
                            )
                        }
                    }
                }
                .ask()
        }
    }