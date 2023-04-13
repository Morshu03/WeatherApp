package com.example.weatherapp.util

sealed class RequestResult<out T>(val data: T?, val message: String?) {
    class Success<out T>(data: T) : RequestResult<T>(data = data, message = null)
    class Error<out T>(message: String?) : RequestResult<T>(data = null, message = message)
}
