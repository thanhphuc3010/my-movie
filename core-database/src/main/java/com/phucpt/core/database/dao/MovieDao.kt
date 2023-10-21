package com.phucpt.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.phucpt.core.database.entity.MovieEntity

/**
 * Created by Phucpt on 11/07/2023 at 13:54
 */

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movieEntities: List<MovieEntity>)

    @Query("SELECT * FROM movie ORDER BY popularity DESC")
    fun getMovieEntities(): PagingSource<Int, MovieEntity>

    @Query("DELETE FROM movie")
    suspend fun clearAll()
}