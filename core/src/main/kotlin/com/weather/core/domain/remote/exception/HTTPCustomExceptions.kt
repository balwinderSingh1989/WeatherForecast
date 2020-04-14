package com.weather.core.domain.remote.exception

import java.io.IOException

class HTTPNotFoundException constructor(message: String) : Exception(message) {
    constructor() : this("No data found")
}

class HTTPBadRequest constructor(message: String) : Throwable(message) {
    constructor() : this("Bad Request")
}

class ServerNotAvailableException(message: String?) : Throwable(message)

class AuthorizationException(message: String) : Exception(message)

class NetworkException(throwable: Throwable) :
    IOException(throwable.message, throwable)

class ProxyException(message: String?) : IOException(message)

class NoContentException(message: String?) : NoSuchElementException(message)

class SessionExpireException constructor(message: String) : Exception(message) {
    constructor() : this("Bearer token can not be null")
}
