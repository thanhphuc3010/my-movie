package com.phucpt.mymovie.data.datasource.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.phucpt.mymovie.data.datasource.remote.api.TheMovieDBApi
import com.phucpt.mymovie.data.mapper.toEntity
import com.phucpt.mymovie.database.AppDatabase
import com.phucpt.mymovie.database.entity.MovieEntity
import com.phucpt.mymovie.database.entity.MovieRemoteKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

/**
 * Created by Phucpt on 11/07/2023 at 14:10
 */

@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator @Inject constructor(
    private val database: AppDatabase,
    private val theMovieDBApi: TheMovieDBApi
) : RemoteMediator<Int, MovieEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {
        val page: Int = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: 1
            }

            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevPage = remoteKeys?.prevKey
                    ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                prevPage
            }

            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextPage = remoteKeys?.nextKey
                    ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                nextPage
            }
        }
        return try {
            val response = theMovieDBApi.fetchDiscoverMovie(page = page)
            val movieDTOs = response.results
            val endOfPaginationReached = movieDTOs.isEmpty()

            val prevKey = if (page == 1) null else page - 1
            val nextKey = if (endOfPaginationReached) null else page + 1

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.movieDao().clearAll()
                    database.movieRemoteKeyDao().deleteAllRemoteKeys()
                }
                val keys = response.results.map { movieDTO ->
                    MovieRemoteKey(
                        id = movieDTO.id,
                        prevKey = prevKey,
                        nextKey = nextKey
                    )
                }
                database.movieRemoteKeyDao().insertAll(remoteKeys = keys)
                database.movieDao().insertAll(movieDTOs.map { it.toEntity() })
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, MovieEntity>
    ): MovieRemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                withContext(Dispatchers.IO) {
                    database.movieRemoteKeyDao().getRemoteKeyById(id = id)
                }
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, MovieEntity>
    ): MovieRemoteKey? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let {
                withContext(Dispatchers.IO) {
                    database.movieRemoteKeyDao().getRemoteKeyById(id = it.id)
                }
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, MovieEntity>
    ): MovieRemoteKey? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let {
                withContext(Dispatchers.IO) {
                    database.movieRemoteKeyDao().getRemoteKeyById(id = it.id)
                }
            }
    }
}