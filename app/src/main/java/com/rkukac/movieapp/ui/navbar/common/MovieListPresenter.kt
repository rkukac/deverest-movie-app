package com.rkukac.movieapp.ui.navbar.common

import androidx.paging.Pager
import com.rkukac.movieapp.ui.navbar.model.UiMovie
import com.rkukac.movieapp.util.paging.common.MovieListPagingKey
import com.rkukac.movieapp.util.paging.common.MovieListPagingSourceData
import com.rkukac.movieapp.util.paging.common.PagingSourceListener

interface MovieListPresenter<PSD : MovieListPagingSourceData, PK : MovieListPagingKey> {

    fun providePagingSource(
        data: PSD,
        pagingSourceListener: PagingSourceListener
    ): Pager<PK, UiMovie>
}