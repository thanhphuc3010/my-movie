package com.phucpt.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.phucpt.core.database.entity.MovieRemoteKey

/**
 * Created by Phucpt on 11/07/2023 at 15:45
 */

@Dao
interface MovieRemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKeys: List<MovieRemoteKey>)

    @Query("SELECT * FROM movie_remote_key WHERE id = :id")
    fun getRemoteKeyById(id: Long): MovieRemoteKey

    @Query("DELETE FROM movie_remote_key")
    suspend fun deleteAllRemoteKeys()
}