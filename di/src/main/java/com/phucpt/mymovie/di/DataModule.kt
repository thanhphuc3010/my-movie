package com.phucpt.mymovie.di

import com.phucpt.core.data.repository.DiscoverRepositoryImpl
import com.phucpt.core.data.repository.JsonPlaceholderRepositoryImpl
import com.phucpt.core.domain.repository.DiscoverRepository
import com.phucpt.core.domain.repository.JsonPlaceholderRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Phucpt on 06/07/2023 at 20:38
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindsJsonPlaceholderRepository(
        jsonPlaceholderRepositoryImpl: JsonPlaceholderRepositoryImpl
    ): JsonPlaceholderRepository

    @Binds
    abstract fun bindsDiscoverRepository(discoverRepositoryImpl: DiscoverRepositoryImpl): DiscoverRepository
}