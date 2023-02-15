package com.rkukac.movieapp.ui.navbar.search

import com.rkukac.movieapp.ui.model.UiStateModel

data class SearchViewState(
    val loading: Boolean = false,
    val error: Boolean = false,
    val errorModel: UiStateModel = UiStateModel(),
    val empty: Boolean = false,
    val emptyModel: UiStateModel = UiStateModel(),
    val searchKeyword: String = ""
)

fun SearchViewState.toLoadingState() = copy(
    loading = true,
    error = false,
    empty = false,
)

fun SearchViewState.toErrorState() = copy(
    loading = false,
    error = true,
    empty = false,
)

fun SearchViewState.toEmptyState() = copy(
    loading = false,
    error = false,
    empty = true,
)

fun SearchViewState.toContentState() = copy(
    loading = false,
    error = false,
    empty = false,
)