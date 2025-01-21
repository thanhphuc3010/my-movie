package com.phucpt.core.network

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

/**
 * Created on 31/03/2024 at 13:29
 *
 * @author phucpt
 */

class CallWithErrorHandling(
    private val delegateCall: Call<Any>
) : Call<Any> by delegateCall {
    override fun enqueue(callback: Callback<Any>) {
        delegateCall.enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                if (response.isSuccessful) {
                    callback.onResponse(call, response)
                } else {
                    callback.onFailure(call, HttpException(response))
                }
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                Log.d("phucpt", "onFailure: ${t.localizedMessage}")
                callback.onFailure(call, t)
            }
        })
    }
}