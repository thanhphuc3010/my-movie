package com.phucpt.mymovie.data.datasource.remote

import com.phucpt.mymovie.codebase.network.Error
import com.phucpt.mymovie.codebase.network.Exception
import com.phucpt.mymovie.codebase.network.ApiResult
import com.phucpt.mymovie.codebase.network.Success
import retrofit2.HttpException
import retrofit2.Response

/**
 * Created by Phucpt on 05/07/2023 at 22:59
 */

abstract class BaseRemoteDataSource {
    suspend fun <T : Any> safeCallApi(execute: suspend () -> Response<T>): ApiResult<T> {
        return try {
            val response = execute.invoke()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                Success(body)
            } else {
                Error(code = response.code(), message = response.message())
            }
        } catch (e: HttpException) {
            Error(code = e.code(), message = e.message)
        } catch (e: Throwable) {
            Exception(e)
        }
    }
}