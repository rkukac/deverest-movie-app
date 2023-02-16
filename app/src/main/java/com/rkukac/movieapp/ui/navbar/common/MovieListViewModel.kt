package com.rkukac.movieapp.ui.navbar.common

import androidx.paging.Pager
import co.zsmb.rainbowcake.base.OneShotEvent
import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import com.rkukac.movieapp.ui.navbar.model.UiMovie
import com.rkukac.movieapp.util.paging.common.MovieListPagingKey
import com.rkukac.movieapp.util.paging.common.MovieListPagingSourceData
import com.rkukac.movieapp.util.paging.common.PagingSourceListener
import timber.log.Timber

abstract class MovieListViewModel<VS : Any, PSD : MovieListPagingSourceData, PK : MovieListPagingKey>(
    initialState: VS,
    private val presenter: MovieListPresenter<PSD, PK>
) : RainbowCakeViewModel<VS>(initialState), PagingSourceListener {

    //region Events
    data class OpenMovieDetailsEvent(
        val id: Int
    ) : OneShotEvent

    object CancelMoviesCollectEvent : OneShotEvent

    data class CollectMoviesEvent(
        val pager: Pager<*, UiMovie>
    ) : OneShotEvent
    //endregion

    fun movieItemSelected(movie: UiMovie) {
        postEvent(OpenMovieDetailsEvent(id = movie.id))
    }

    fun loadingStateError() {
        cancelMoviesCollect()
        setErrorState()
    }

    //region Collect
    protected fun collectMovies() {
        setLoadingState()

        try {
            cancelMoviesCollect()
            postEvent(
                CollectMoviesEvent(
                    pager = presenter.providePagingSource(
                        data = getPagingSourceData(),
                        pagingSourceListener = this@MovieListViewModel
                    )
                )
            )
        } catch (e: Exception) {
            Timber.d(e, "Failed to get data!")
            setErrorState()
        }
    }

    abstract fun getPagingSourceData(): PSD

    private fun cancelMoviesCollect() {
        postEvent(CancelMoviesCollectEvent)
    }
    //endregion

    //region PagingSourceListener
    override fun onListEmpty() {
        setEmptyState()
    }

    override fun onInitialDataReceived() {
        setContentState()
    }
    //endregion

    //region States
    abstract fun setLoadingState()

    abstract fun setErrorState()

    abstract fun setEmptyState()

    abstract fun setContentState()
    //endregion
}