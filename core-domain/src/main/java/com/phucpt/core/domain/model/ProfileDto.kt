package com.phucpt.core.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Phucpt on 31/01/2024 at 21:16
 */

data class ProfileDto(
    @SerializedName("Acquirers")
    val acquirersMap: Map<String, Any> = emptyMap(),
//    val acquirers: List<Acquirer> = emptyList()
)
