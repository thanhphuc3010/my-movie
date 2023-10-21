package com.phucpt.core.data.mapper

import com.phucpt.core.database.entity.MovieEntity
import com.phucpt.core.domain.model.Movie
import com.phucpt.core.network.dto.MovieDto

/**
 * Created by Phucpt on 11/07/2023 at 16:26
 */

fun MovieDto.toEntity() = MovieEntity(
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

fun MovieEntity.toModel() = Movie(
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