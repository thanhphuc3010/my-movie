package com.phucpt.mymovie.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Phucpt on 11/07/2023 at 13:55
 */

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    val id: Long,
    val adult: Boolean = false,
    @ColumnInfo("backdrop_path")
    val backdropPath: String? = null,
//    @ColumnInfo("genre_ids")
//    val genreIds: List<Int> = listOf(),
    @ColumnInfo("original_language")
    val originalLanguage: String = "",
    @ColumnInfo("original_title")
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    @ColumnInfo("poster_path")
    val posterPath: String? = null,
    @ColumnInfo("release_date")
    val releaseDate: String? = null,
    val title: String = "",
    val video: Boolean = false,
    @ColumnInfo("vote_average")
    val voteAverage: Double = 0.0,
    @ColumnInfo("vote_count")
    val voteCount: Long = 0
)
