package com.phucpt.mymovie.data.datasource.remote.api

import com.phucpt.mymovie.data.dto.DiscoverMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Phucpt on 10/07/2023 at 16:17
 */

interface TheMovieDBApi {
    /**
     * @param [includeAdult] Specify the results include adult or not.
     * @param [page] Specify the page of results to query.
     *
     * @return [DiscoverMovieResponse] response.
     */
    @GET("/3/discover/movie")
    suspend fun fetchDiscoverMovie(
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("page") page: Int
    ): DiscoverMovieResponse
}