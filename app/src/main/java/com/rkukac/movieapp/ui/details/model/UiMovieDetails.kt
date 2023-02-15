package com.rkukac.movieapp.ui.details.model

import com.rkukac.movieapp.domain.model.DomainMovieDetails
import java.math.BigDecimal

data class UiMovieDetails(
    val title: String,
    val overview: String,
    val releaseDate: String,
    val image: String?,
    val rating: String,
    val budget: String,
    val revenue: String,
    val popularity: String
)

fun DomainMovieDetails.toUiMovieDetails(
    imageFormatterBlock: (String?) -> String?,
    amountFormatterBlock: (BigDecimal?) -> String
) = UiMovieDetails(
    title = title,
    overview = overview ?: "",
    releaseDate = releaseDate ?: "",
    image = imageFormatterBlock.invoke(image),
    rating = rating.toString(),
    budget = amountFormatterBlock.invoke(budget),
    revenue = amountFormatterBlock.invoke(revenue),
    popularity = popularity?.toString() ?: ""
)