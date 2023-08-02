package com.phucpt.mymovie.data.mapper

import com.phucpt.mymovie.data.dto.MovieDTO
import com.phucpt.mymovie.database.entity.MovieEntity

/**
 * Created by Phucpt on 11/07/2023 at 16:26
 */

fun MovieDTO.toEntity() = MovieEntity(
    id = id,
    adult = adult,
    backdropPath = backdropPath,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    releaseDate = releaseDate,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount
)