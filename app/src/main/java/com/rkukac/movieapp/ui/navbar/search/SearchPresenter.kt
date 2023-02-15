package com.rkukac.movieapp.ui.navbar.search

import androidx.paging.Pager
import com.rkukac.movieapp.domain.MovieInteractor
import com.rkukac.movieapp.ui.model.UiStateModel
import com.rkukac.movieapp.ui.model.toUiStateModel
import com.rkukac.movieapp.ui.navbar.common.MovieListPresenter
import com.rkukac.movieapp.ui.navbar.model.UiMovie
import com.rkukac.movieapp.util.paging.SearchPagingKey
import com.rkukac.movieapp.util.paging.common.PagingSourceListener
import javax.inject.Inject

class SearchPresenter @Inject constructor(
    private val interactor: MovieInteractor
) : MovieListPresenter<SearchPagingSourceData, SearchPagingKey> {

    fun getSearchErrorStateModel(): UiStateModel =
        interactor.getSearchErrorStateModel().toUiStateModel()

    fun getSearchEmptyStateModel(): UiStateModel =
        interactor.getSearchEmptyStateModel().toUiStateModel()

    fun getEmptySearchKeywordMessage() = interactor.getEmptySearchKeywordMessage()

    override fun providePagingSource(
        data: SearchPagingSourceData,
        pagingSourceListener: PagingSourceListener
    ): Pager<SearchPagingKey, UiMovie> {
        return interactor.provideSearchPagingSource(
            searchKeyword = data.searchKeyword,
            pagingSourceListener = pagingSourceListener
        )
    }
}