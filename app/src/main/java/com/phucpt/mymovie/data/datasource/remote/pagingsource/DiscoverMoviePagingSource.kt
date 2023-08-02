package com.phucpt.mymovie.data.datasource.remote.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.phucpt.mymovie.data.datasource.remote.api.TheMovieDBApi
import com.phucpt.mymovie.data.dto.MovieDTO
import com.phucpt.mymovie.data.repository.DiscoverRepositoryImpl.Companion.NETWORK_PAGE_SIZE
import retrofit2.HttpException
import java.io.IOException

/**
 * Created by Phucpt on 10/07/2023 at 16:07
 */

private const val STARTING_PAGE_INDEX = 1

class DiscoverMoviePagingSource(private val theMovieDBApi: TheMovieDBApi) :
    PagingSource<Int, MovieDTO>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDTO> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = theMovieDBApi.fetchDiscoverMovie(page = position)
            val movies = response.results
            val nextKey = if (movies.isEmpty()) {
                null
            } else {
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = movies,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieDTO>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}