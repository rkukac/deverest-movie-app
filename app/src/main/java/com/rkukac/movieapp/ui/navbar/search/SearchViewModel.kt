package com.rkukac.movieapp.ui.navbar.search

import co.zsmb.rainbowcake.base.OneShotEvent
import com.rkukac.movieapp.ui.navbar.common.MovieListViewModel
import com.rkukac.movieapp.util.paging.SearchPagingKey
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val presenter: SearchPresenter
) : MovieListViewModel<SearchViewState, SearchPagingSourceData, SearchPagingKey>(
    initialState = SearchViewState(),
    presenter = presenter
) {

    //region Events
    data class ShowSnackMessage(
        val message: String
    ) : OneShotEvent

    object HideKeyboardEvent : OneShotEvent
    //endregion

    fun loadInitialData() = execute {
        viewState = viewState.copy(
            errorModel = presenter.getSearchErrorStateModel(),
            emptyModel = presenter.getSearchEmptyStateModel()
        )
    }

    fun searchButtonClick(searchKeyword: String?) = execute {
        if (searchKeyword.isNullOrEmpty()) {
            postEvent(ShowSnackMessage(message = presenter.getEmptySearchKeywordMessage()))
            return@execute
        }

        postEvent(HideKeyboardEvent)

        viewState = viewState.copy(searchKeyword = searchKeyword)
        collectMovies()
    }

    override fun getPagingSourceData(): SearchPagingSourceData = SearchPagingSourceData(
        searchKeyword = viewState.searchKeyword
    )

    //region States
    override fun setLoadingState() {
        viewState = viewState.toLoadingState()
    }

    override fun setErrorState() {
        viewState = viewState.toErrorState()
    }

    override fun setEmptyState() {
        viewState = viewState.toEmptyState()
    }

    override fun setContentState() {
        viewState = viewState.toContentState()
    }
    //endregion
}