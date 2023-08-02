package com.phucpt.mymovie.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.phucpt.mymovie.database.dao.MovieDao
import com.phucpt.mymovie.database.dao.MovieRemoteKeyDao
import com.phucpt.mymovie.database.entity.MovieEntity
import com.phucpt.mymovie.database.entity.MovieRemoteKey

/**
 * Created by Phucpt on 11/07/2023 at 13:51
 */

@Database(
    entities = [
        MovieEntity::class,
        MovieRemoteKey::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun movieRemoteKeyDao(): MovieRemoteKeyDao
}