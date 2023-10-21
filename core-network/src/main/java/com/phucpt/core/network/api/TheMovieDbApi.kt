package com.phucpt.core.network.api

import com.phucpt.core.network.dto.DiscoverMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by phucpt on 10/11/2023
 */

interface TheMovieDbApi {
    @GET("/3/discover/movie")
    suspend fun fetchDiscoverMovie(
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("page") page: Int
    ): DiscoverMovieResponse
}