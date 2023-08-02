package com.phucpt.mymovie.codebase.network

/**
 * Created by Phucpt on 05/07/2023 at 22:44
 */

sealed interface ApiResult<out T>

/**
 * @author phucpt
 *
 * Represents a network result that successfully received a response containing body data.
 */
data class Success<T>(val data: T) : ApiResult<T>

sealed interface Failure<T> : ApiResult<T>

data class Error<T>(val code: Int, val message: String?) : Failure<T> {
    override fun toString(): String {
        return if (!message.isNullOrEmpty()) {
            message
        } else {
            "[ApiResponse.Failure.Error-$code](errorMessage=$message)"
        }
    }
}

data class Exception<T>(val exception: Throwable) : Failure<T> {
    val message: String? = exception.localizedMessage
    override fun toString() = "[ApiResponse.Failure.Exception](message=${message})"
}