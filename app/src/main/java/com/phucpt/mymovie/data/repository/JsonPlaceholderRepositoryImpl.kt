package com.phucpt.mymovie.data.repository

import com.phucpt.mymovie.data.datasource.remote.api.JsonPlaceholderApi
import com.phucpt.mymovie.domain.repository.JsonPlaceholderRepository
import javax.inject.Inject

/**
 * Created by Phucpt on 06/07/2023 at 14:34
 */

class JsonPlaceholderRepositoryImpl @Inject constructor(
    private val jsonPlaceholderApi: JsonPlaceholderApi
) : JsonPlaceholderRepository {
}