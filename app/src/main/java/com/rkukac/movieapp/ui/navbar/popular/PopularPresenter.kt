package com.rkukac.movieapp.ui.navbar.popular

import androidx.paging.Pager
import com.rkukac.movieapp.domain.MovieInteractor
import com.rkukac.movieapp.ui.model.UiStateModel
import com.rkukac.movieapp.ui.model.toUiStateModel
import com.rkukac.movieapp.ui.navbar.common.MovieListPresenter
import com.rkukac.movieapp.ui.navbar.model.UiMovie
import com.rkukac.movieapp.util.paging.PopularPagingKey
import com.rkukac.movieapp.util.paging.common.PagingSourceListener
import javax.inject.Inject

class PopularPresenter @Inject constructor(
    private val interactor: MovieInteractor
) : MovieListPresenter<PopularPagingSourceData, PopularPagingKey> {
    override fun providePagingSource(
        data: PopularPagingSourceData,
        pagingSourceListener: PagingSourceListener
    ): Pager<PopularPagingKey, UiMovie> {
        return interactor.providePopularPagingSource(pagingSourceListener = pagingSourceListener)
    }

    fun getPopularErrorStateModel(): UiStateModel =
        interactor.getPopularErrorStateModel().toUiStateModel()

    fun getPopularEmptyStateModel(): UiStateModel =
        interactor.getPopularEmptyStateModel().toUiStateModel()

}