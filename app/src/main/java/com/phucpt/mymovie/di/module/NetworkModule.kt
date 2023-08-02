package com.phucpt.mymovie.di.module

import com.phucpt.mymovie.BuildConfig
import com.phucpt.mymovie.data.datasource.remote.api.JsonPlaceholderApi
import com.phucpt.mymovie.data.datasource.remote.api.TheMovieDBApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Phucpt on 06/07/2023 at 00:24
 */

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideJsonPlaceholderApi(retrofit: Retrofit): JsonPlaceholderApi {
        return retrofit.create(JsonPlaceholderApi::class.java)
    }

    @Provides
    fun provideTheMovieDBApi(retrofit: Retrofit): TheMovieDBApi {
        return retrofit.create(TheMovieDBApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val builder = chain.request().newBuilder()
                builder.header("accept", "application/json")
                builder.header("Authorization", "Bearer ${BuildConfig.ACCESS_TOKEN_AUTH}")
                chain.proceed(builder.build())
            }
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }
}