package com.rkukac.movieapp.domain.model

import com.rkukac.movieapp.data.network.model.MovieDetails
import java.math.BigDecimal

data class DomainMovieDetails(
    val id: Int,
    val title: String,
    val overview: String?,
    val releaseDate: String?,
    val image: String?,
    val rating: Double?,
    val budget: BigDecimal?,
    val revenue: BigDecimal?,
    val popularity: Double?
)

fun MovieDetails.toDomainMovieDetails() = DomainMovieDetails(
    id = id,
    title = title,
    overview = overview,
    releaseDate = releaseDate,
    image = image,
    rating = rating,
    budget = budget,
    revenue = revenue,
    popularity = popularity
)