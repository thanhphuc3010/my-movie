package com.phucpt.core.domain.model

/**
 * Created by phucpt on 10/12/2023
 */

data class Movie(
    val id: Long,
    val adult: Boolean = false,
    val backdropPath: String? = null,
    val genreIds: List<Int> = listOf(),
    val originalLanguage: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val posterPath: String? = null,
    val releaseDate: String? = null,
    val title: String = "",
    val video: Boolean = false,
    val voteAverage: Double = 0.0,
    val voteCount: Long = 0
)
