package com.rkukac.movieapp.domain.model

import com.rkukac.movieapp.data.network.model.Movie
import java.math.BigDecimal

data class DomainMovie(
    val id: Int,
    val title: String,
    val image: String? = null,
    val rating: Double,
    val budget: BigDecimal = BigDecimal.ZERO,
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
    amountFormatterBlock: (BigDecimal?) -> String
) = DomainMovie(
    id = id,
    title = title,
    image = imageFormatterBlock.invoke(image),
    rating = rating ?: 0.0,
    budget = budget ?: BigDecimal.ZERO,
    budgetString = budget?.let { amountFormatterBlock.invoke(it) } ?: ""
)