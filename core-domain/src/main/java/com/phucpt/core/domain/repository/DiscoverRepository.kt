package com.phucpt.core.domain.repository

import androidx.paging.PagingData
import com.phucpt.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

/**
 * Created by Phucpt on 10/07/2023 at 17:23
 */

interface DiscoverRepository : BaseRepository {
    fun getMoviesStream(): Flow<PagingData<Movie>>
}