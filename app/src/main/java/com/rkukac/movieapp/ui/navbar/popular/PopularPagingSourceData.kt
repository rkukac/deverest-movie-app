package com.rkukac.movieapp.ui.navbar.popular

import com.rkukac.movieapp.util.paging.common.MovieListPagingSourceData

data class PopularPagingSourceData(
    val text: String = ""
) : MovieListPagingSourceData