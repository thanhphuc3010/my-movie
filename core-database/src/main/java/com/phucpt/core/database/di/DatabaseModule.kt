package com.phucpt.core.database.di

import android.content.Context
import androidx.room.Room
import com.phucpt.core.database.AppDatabase
import com.phucpt.core.database.dao.MovieDao
import com.phucpt.core.database.dao.MovieRemoteKeyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Phucpt on 11/07/2023 at 13:59
 */

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app-database"
        ).build()

    @Provides
    fun providesMovieDao(database: AppDatabase): MovieDao = database.movieDao()

    @Provides
    fun providesMovieRemoteKeyDao(database: AppDatabase): MovieRemoteKeyDao =
        database.movieRemoteKeyDao()
}