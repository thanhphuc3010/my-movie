package com.phucpt.mymovie.domain.repository

import com.phucpt.mymovie.codebase.network.ApiResult
import com.phucpt.mymovie.codebase.network.Error
import com.phucpt.mymovie.codebase.network.Exception
import com.phucpt.mymovie.codebase.network.Success
import retrofit2.HttpException
import retrofit2.Response

/**
 * Created by Phucpt on 06/07/2023 at 20:32
 */

interface BaseRepository {
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