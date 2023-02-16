package com.rkukac.movieapp.ui.navbar.model

import com.rkukac.movieapp.domain.model.DomainMovie

data class UiMovie(
    val id: Int,
    val title: String,
    val image: String?,
    val rating: Double,
    val budgetString: String
)

fun DomainMovie.toUiMovie() = UiMovie(
    id = id,
    title = title,
    image = image,
    rating = rating,
    budgetString = budgetString
)