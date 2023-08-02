package com.phucpt.mymovie.data.datasource.remote.api

import com.phucpt.mymovie.data.datasource.remote.model.PostDTO
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Phucpt on 06/07/2023 at 09:27
 */

interface JsonPlaceholderApi {
    @GET("/posts")
    suspend fun getAllPosts(): Response<List<PostDTO>>
}