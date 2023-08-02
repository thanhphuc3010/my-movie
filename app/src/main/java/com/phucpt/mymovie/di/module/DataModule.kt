package com.phucpt.mymovie.di.module

import com.phucpt.mymovie.data.repository.DiscoverRepositoryImpl
import com.phucpt.mymovie.data.repository.JsonPlaceholderRepositoryImpl
import com.phucpt.mymovie.domain.repository.DiscoverRepository
import com.phucpt.mymovie.domain.repository.JsonPlaceholderRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Phucpt on 06/07/2023 at 20:38
 */

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsJsonPlaceholderRepository(
        jsonPlaceholderRepositoryImpl: JsonPlaceholderRepositoryImpl
    ): JsonPlaceholderRepository

    @Binds
    fun bindsDiscoverRepository(discoverRepositoryImpl: DiscoverRepositoryImpl): DiscoverRepository
}