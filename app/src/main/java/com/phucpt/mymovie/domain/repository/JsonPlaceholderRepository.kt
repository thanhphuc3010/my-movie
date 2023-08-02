package com.phucpt.mymovie.domain.repository

import com.phucpt.mymovie.codebase.network.ApiResult
import com.phucpt.mymovie.data.datasource.remote.model.PostDTO
import kotlinx.coroutines.flow.Flow

/**
 * Created by Phucpt on 06/07/2023 at 14:33
 */

interface JsonPlaceholderRepository : BaseRepository {
    suspend fun getAllPosts(): Flow<ApiResult<List<PostDTO>>>
}