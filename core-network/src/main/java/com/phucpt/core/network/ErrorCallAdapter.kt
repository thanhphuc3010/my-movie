package com.phucpt.core.network

import retrofit2.Call
import retrofit2.CallAdapter

/**
 * Created on 31/03/2024 at 13:08
 *
 * @author phucpt
 */

class ErrorsCallAdapter(
    private val delegateAdapter: CallAdapter<Any, Call<*>>
) : CallAdapter<Any, Call<*>> by delegateAdapter {

    override fun adapt(call: Call<Any>): Call<*> {
        return CallWithErrorHandling(call)
    }
}