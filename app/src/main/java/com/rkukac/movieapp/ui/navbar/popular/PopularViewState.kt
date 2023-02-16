package com.rkukac.movieapp.ui.navbar.popular

import com.rkukac.movieapp.ui.model.UiStateModel

data class PopularViewState(
    val loading: Boolean = false,
    val error: Boolean = false,
    val errorModel: UiStateModel = UiStateModel(),
    val empty: Boolean = false,
    val emptyModel: UiStateModel = UiStateModel()
)

fun PopularViewState.toLoadingState() = copy(
    loading = true,
    error = false,
    empty = false,
)

fun PopularViewState.toErrorState() = copy(
    loading = false,
    error = true,
    empty = false,
)

fun PopularViewState.toEmptyState() = copy(
    loading = false,
    error = false,
    empty = true,
)

fun PopularViewState.toContentState() = copy(
    loading = false,
    error = false,
    empty = false,
)