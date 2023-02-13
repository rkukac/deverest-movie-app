package com.rkukac.movieapp.domain.model

import com.rkukac.movieapp.data.network.model.MovieDetails

data class DomainMovieDetails(
    val id: Int,
    val title: String,
    val overview: String? = null,
    val releaseDate: String,
    val image: String? = null,
    val rating: Double,
    val budget: Int
)

fun MovieDetails.toDomainMovieDetails() = DomainMovieDetails(
    id = id,
    title = title,
    overview = overview,
    releaseDate = releaseDate,
    image = image,
    rating = rating,
    budget = budget
)