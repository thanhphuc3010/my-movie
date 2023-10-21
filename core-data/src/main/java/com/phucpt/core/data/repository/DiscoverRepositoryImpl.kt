package com.phucpt.core.data.repository

import androidx.paging.*
import com.phucpt.core.data.datasource.paging.MovieRemoteMediator
import com.phucpt.core.data.mapper.toModel
import com.phucpt.core.database.AppDatabase
import com.phucpt.core.database.dao.MovieDao
import com.phucpt.core.domain.model.Movie
import com.phucpt.core.domain.repository.DiscoverRepository
import com.phucpt.core.network.api.TheMovieDbApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by Phucpt on 10/07/2023 at 17:25
 */

class DiscoverRepositoryImpl @Inject constructor(
    private val theMovieDBApi: TheMovieDbApi,
    private val movieDao: MovieDao,
    private val database: AppDatabase
) : DiscoverRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getMoviesStream(): Flow<PagingData<Movie>> {
        val pagingSourceFactory = { movieDao.getMovieEntities() }
        return Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = MovieRemoteMediator(database, theMovieDBApi),
            pagingSourceFactory = pagingSourceFactory,
        ).flow.map {
            it.map { it.toModel() }
        }
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }
}