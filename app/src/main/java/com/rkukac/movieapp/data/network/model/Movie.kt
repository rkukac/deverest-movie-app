package com.rkukac.movieapp.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Movie(
    @Json(name = "id")
    val id: Int,
    @Json(name = "title")
    val title: String,
    @Json(name = "poster_path")
    val image: String? = null,
    @Json(name = "vote_average")
    val rating: Double
)
