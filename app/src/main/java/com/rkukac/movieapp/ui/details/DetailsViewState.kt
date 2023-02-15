package com.rkukac.movieapp.ui.details

import com.rkukac.movieapp.ui.details.model.UiMovieDetails
import com.rkukac.movieapp.ui.model.UiStateModel

data class DetailsViewState(
    val loading: Boolean = true,
    val error: Boolean = false,
    val errorModel: UiStateModel = UiStateModel(),
    val id: Int = 0,
    val details: UiMovieDetails? = null
)

fun DetailsViewState.toLoadingState() = copy(
    loading = true,
    error = false
)

fun DetailsViewState.toErrorState() = copy(
    loading = false,
    error = true
)

fun DetailsViewState.toContentState(details: UiMovieDetails) = copy(
    loading = false,
    error = false,
    details = details
)