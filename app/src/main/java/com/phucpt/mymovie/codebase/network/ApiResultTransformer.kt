package com.phucpt.mymovie.codebase.network

/**
 * Created by Phucpt on 06/07/2023 at 14:22
 */

suspend fun <T> ApiResult<T>.onSuccess(
    executable: suspend (T) -> Unit
): ApiResult<T> = apply {
    if (this is Success<T>) {
        executable.invoke(data)
    }
}

inline fun <T> ApiResult<T>.onFailure(
    block: Failure<T>.() -> Unit
): ApiResult<T> {
    if (this is Failure<T>) {
        block(this)
    }
    return this
}

fun <T : Any> ApiResult<T>.onError(
    executable: (code: Int, message: String?) -> Unit
): ApiResult<T> = apply {
    if (this is Error<T>) {
        executable(code, message)
    }
}

fun <T : Any> ApiResult<T>.onException(
    executable: (e: Throwable) -> Unit
): ApiResult<T> = apply {
    if (this is Exception<T>) {
        executable(exception)
    }
}

fun <T> Failure<T>.message() = when (this) {
    is Error -> toString()
    is Exception -> toString()
}