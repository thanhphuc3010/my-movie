package com.phucpt.mymovie.data.datasource.remote.model

/**
 * Created by Phucpt on 06/07/2023 at 09:28
 */

data class PostDTO(
    val id: String,
    val userId: String,
    val title: String?,
    val body: String?
)
