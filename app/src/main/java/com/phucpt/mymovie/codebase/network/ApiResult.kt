package com.phucpt.mymovie.codebase.network

/**
 * Created by Phucpt on 05/07/2023 at 22:44
 */

sealed interface ApiResult<T : Any>

class ApiSuccess<T : Any>(val data: T) : ApiResult<T>
class ApiError<T : Any>(val code: Int, val message: String?) : ApiResult<T>
class ApiException<T : Any>(val e: Throwable) : ApiResult<T>