package com.rkukac.movieapp.domain.model

import com.rkukac.movieapp.data.network.model.Movie

data class DomainMovie(
    val id: Int,
    val title: String,
    val image: String? = null,
    val rating: Double
)

fun Movie.toDomainMovie() = DomainMovie(
    id = id,
    title = title,
    image = image,
    rating = rating
)