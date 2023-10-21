package com.phucpt.core.data

import com.phucpt.core.network.ApiResult
import com.phucpt.core.network.Error
import com.phucpt.core.network.Exception
import com.phucpt.core.network.Success
import retrofit2.HttpException

/**
 * Created by phucpt on 10/12/2023
 */

abstract class BaseRepositoryImpl {
    suspend fun <T : Any> safeCallApi(execute: suspend () -> T): ApiResult<T> {
        return try {
            val data = execute.invoke()
            Success(data)
        } catch (e: HttpException) {
            Error(code = e.code(), message = e.message())
        } catch (e: Throwable) {
            Exception(e)
        }
    }
}