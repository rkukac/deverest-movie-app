package com.rkukac.movieapp.ui.navbar.popular

import com.rkukac.movieapp.ui.navbar.common.MovieListViewModel
import com.rkukac.movieapp.util.paging.PopularPagingKey
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val presenter: PopularPresenter
) : MovieListViewModel<PopularViewState, PopularPagingSourceData, PopularPagingKey>(
    initialState = PopularViewState(),
    presenter = presenter
) {
    override fun getPagingSourceData(): PopularPagingSourceData = PopularPagingSourceData()

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

    fun loadInitialData() = execute {
        viewState = viewState.copy(
            errorModel = presenter.getPopularErrorStateModel(),
            emptyModel = presenter.getPopularEmptyStateModel()
        )

        collectMovies()
    }

    fun errorButtonClick() {
        collectMovies()
    }
}