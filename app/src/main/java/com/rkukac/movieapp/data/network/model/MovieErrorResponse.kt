package com.rkukac.movieapp.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieErrorResponse(
    @Json(name = "status_message")
    val message: String? = null,
    @Json(name = "status_code")
    val code: Int? = null,
)