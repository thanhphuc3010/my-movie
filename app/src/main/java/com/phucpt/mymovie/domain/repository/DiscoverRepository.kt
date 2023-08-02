package com.phucpt.mymovie.domain.repository

import androidx.paging.PagingData
import com.phucpt.mymovie.data.dto.MovieDTO
import com.phucpt.mymovie.database.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by Phucpt on 10/07/2023 at 17:23
 */

interface DiscoverRepository : BaseRepository {
    fun getMoviesStream(): Flow<PagingData<MovieEntity>>
}