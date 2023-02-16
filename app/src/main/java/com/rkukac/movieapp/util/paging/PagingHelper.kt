package com.rkukac.movieapp.util.paging

import androidx.paging.Pager
import com.rkukac.movieapp.ui.navbar.model.UiMovie
import com.rkukac.movieapp.util.config.ConfigHelper
import com.rkukac.movieapp.util.paging.common.PagingSourceListener
import javax.inject.Inject
import javax.inject.Provider

class PagingHelper @Inject constructor(
    private val configHelper: ConfigHelper,
    private val searchPagingSourceProvider: Provider<SearchPagingSource>,
    private val popularPagingSourceProvider: Provider<PopularPagingSource>,
) {

    companion object {
        const val INITIAL_PAGE = 1
    }

    fun provideSearchPagingSource(
        searchKeyword: String,
        pagingSourceListener: PagingSourceListener,
        pagingHelperListener: PagingHelperListener
    ): Pager<SearchPagingKey, UiMovie> = Pager(
        config = configHelper.provideDefaultPageConfig(),
        initialKey = SearchPagingKey(
            page = INITIAL_PAGE,
            searchKeyword = searchKeyword
        )
    ) {
        searchPagingSourceProvider.get().apply {
            this.pagingSourceListener = pagingSourceListener
            this.pagingHelperListener = pagingHelperListener
        }
    }

    fun providePopularPagingSource(
        pagingSourceListener: PagingSourceListener,
        pagingHelperListener: PagingHelperListener
    ): Pager<PopularPagingKey, UiMovie> = Pager(
        config = configHelper.provideDefaultPageConfig(),
        initialKey = PopularPagingKey(
            page = INITIAL_PAGE
        )
    ) {
        popularPagingSourceProvider.get().apply {
            this.pagingSourceListener = pagingSourceListener
            this.pagingHelperListener = pagingHelperListener
        }
    }
}