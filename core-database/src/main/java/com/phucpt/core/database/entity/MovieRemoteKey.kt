package com.phucpt.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Phucpt on 11/07/2023 at 14:57
 */

@Entity("movie_remote_key")
data class MovieRemoteKey(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val prevKey: Int?,
    val nextKey: Int?
)