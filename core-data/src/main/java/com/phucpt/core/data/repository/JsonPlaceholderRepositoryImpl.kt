package com.phucpt.core.data.repository

import android.graphics.Bitmap
import androidx.collection.LruCache
import com.phucpt.core.domain.repository.JsonPlaceholderRepository
import com.phucpt.core.network.api.TheMovieDbApi
import okhttp3.internal.cache.DiskLruCache
import javax.inject.Inject

/**
 * Created by Phucpt on 06/07/2023 at 14:34
 */

class JsonPlaceholderRepositoryImpl @Inject constructor(
    private val theMovieDbApi: TheMovieDbApi
) : JsonPlaceholderRepository {
    val lruCache = LruCache<Int, Bitmap>(4)
}