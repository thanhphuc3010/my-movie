package com.phucpt.core.network

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * Created on 31/03/2024 at 12:57
 *
 * @author phucpt
 */

class ErrorCallAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        return when (getRawType(returnType)) {
            Call::class.java -> {
                val delegate = retrofit.nextCallAdapter(this, returnType, annotations)
                (delegate as? CallAdapter<Any, Call<*>>)?.let { ErrorsCallAdapter(it) }
            }

            else -> null
        }
    }
}