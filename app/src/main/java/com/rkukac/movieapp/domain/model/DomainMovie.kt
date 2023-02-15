package com.rkukac.movieapp.domain.model

import com.rkukac.movieapp.data.network.model.Movie

data class DomainMovie(
    val id: Int,
    val title: String,
    val image: String? = null,
    val rating: Double,
    val budget: Int = 0,
    val budgetString: String = ""
)

fun Movie.toDomainMovie() = DomainMovie(
    id = id,
    title = title,
    image = image,
    rating = rating
)

fun DomainMovieDetails.toDomainMovie(
    imageFormatterBlock: (String?) -> String?,
    budgetFormatterBlock: (Int) -> String
) = DomainMovie(
    id = id,
    title = title,
    image = imageFormatterBlock.invoke(image),
    rating = rating,
    budget = budget,
    budgetString = budgetFormatterBlock.invoke(budget)
)