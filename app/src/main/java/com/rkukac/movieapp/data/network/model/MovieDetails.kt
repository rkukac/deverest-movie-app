package com.rkukac.movieapp.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDetails(
    @Json(name = "id")
    val id: Int,
    @Json(name = "title")
    val title: String,
    @Json(name = "overview")
    val overview: String? = null,
    @Json(name = "release_date")
    val releaseDate: String,
    @Json(name = "poster_path")
    val image: String? = null,
    @Json(name = "vote_average")
    val rating: Double,
    @Json(name = "budget")
    val budget: Int
)