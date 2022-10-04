package com.rafiadly.otakubandung.core.data

sealed class Resource<T>(val data: T?  = null, val message: String? = null) {
    class Success<T>(data: T?): com.rafiadly.otakubandung.core.data.Resource<T>(data)
    class Error<T>(message: String?, data: T? = null): com.rafiadly.otakubandung.core.data.Resource<T>(data, message)
    class Loading<T>(data: T? = null): com.rafiadly.otakubandung.core.data.Resource<T>(data)
}