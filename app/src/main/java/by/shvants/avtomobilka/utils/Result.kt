package by.shvants.avtomobilka.utils

sealed class RequestResult<out T> {
    data class Success<out T>(val value: T) : RequestResult<T>()
    data class Error(val exception: Throwable) : RequestResult<Nothing>()
}