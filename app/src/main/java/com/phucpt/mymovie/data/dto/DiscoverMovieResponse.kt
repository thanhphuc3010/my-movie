package com.phucpt.mymovie.data.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by Phucpt on 10/07/2023 at 16:59
 */

data class DiscoverMovieResponse(
    val page: Int,
    val results: List<MovieDTO>,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)
