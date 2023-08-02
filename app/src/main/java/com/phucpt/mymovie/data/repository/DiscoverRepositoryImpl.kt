package com.phucpt.mymovie.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.phucpt.mymovie.data.datasource.paging.MovieRemoteMediator
import com.phucpt.mymovie.data.datasource.remote.api.TheMovieDBApi
import com.phucpt.mymovie.database.AppDatabase
import com.phucpt.mymovie.database.dao.MovieDao
import com.phucpt.mymovie.database.entity.MovieEntity
import com.phucpt.mymovie.domain.repository.DiscoverRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Phucpt on 10/07/2023 at 17:25
 */

class DiscoverRepositoryImpl @Inject constructor(
    private val theMovieDBApi: TheMovieDBApi,
    private val movieDao: MovieDao,
    private val database: AppDatabase
) : DiscoverRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getMoviesStream(): Flow<PagingData<MovieEntity>> {
        val pagingSourceFactory = { movieDao.getMovieEntities() }
            return Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = MovieRemoteMediator(database, theMovieDBApi),
            pagingSourceFactory = pagingSourceFactory,
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }
}