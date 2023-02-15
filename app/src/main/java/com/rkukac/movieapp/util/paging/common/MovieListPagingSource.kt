package com.rkukac.movieapp.util.paging.common

import androidx.paging.PagingSource
import androidx.paging.PagingState
import co.zsmb.rainbowcake.withIOContext
import com.rkukac.movieapp.domain.model.DomainMovieListBase
import com.rkukac.movieapp.ui.navbar.model.UiMovie
import com.rkukac.movieapp.ui.navbar.model.toUiMovie
import com.rkukac.movieapp.util.paging.PagingHelper
import com.rkukac.movieapp.util.paging.PagingHelperListener
import timber.log.Timber

abstract class MovieListPagingSource<PK : MovieListPagingKey, DV : DomainMovieListBase> :
    PagingSource<PK, UiMovie>() {

    var pagingSourceListener: PagingSourceListener? = null

    var pagingHelperListener: PagingHelperListener? = null

    override fun getRefreshKey(state: PagingState<PK, UiMovie>): PK? = null

    override suspend fun load(params: LoadParams<PK>): LoadResult<PK, UiMovie> = withIOContext {
        try {
            if (params.key == null) {
                return@withIOContext LoadResult.Error<PK, UiMovie>(
                    NullPointerException("Paging key is missing!")
                )
            }

            val key = requireNotNull(params.key)
            val response = loadDomainValue(key)
                ?: return@withIOContext LoadResult.Error<PK, UiMovie>(
                    NullPointerException("Response is null!")
                )

            checkEmptyList(response)

            LoadResult.Page(
                data = convertDomainValue(domainValue = response),
                prevKey = null,
                nextKey = getNextKey(key, response)
            ).also {
                checkInitialDataReceived(key.page, response)
            }
        } catch (e: Exception) {
            Timber.d(e, "Failed to load movies!")
            LoadResult.Error(e)
        }
    }

    abstract suspend fun loadDomainValue(key: PK): DV?

    private fun checkEmptyList(response: DV) {
        if (response.page == PagingHelper.INITIAL_PAGE && response.movies.isEmpty()) {
            pagingSourceListener?.onListEmpty()
        }
    }

    private fun convertDomainValue(
        domainValue: DV
    ): List<UiMovie> {
        return domainValue.movies.map {
            it.toUiMovie()
        }
    }

    abstract fun getNextKey(key: PK, response: DV): PK?

    private fun checkInitialDataReceived(page: Int, response: DV) {
        if (page == PagingHelper.INITIAL_PAGE && response.movies.isNotEmpty()) {
            pagingSourceListener?.onInitialDataReceived()
        }
    }
}