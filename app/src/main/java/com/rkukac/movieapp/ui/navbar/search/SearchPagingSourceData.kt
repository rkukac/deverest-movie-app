package com.rkukac.movieapp.ui.navbar.search

import com.rkukac.movieapp.util.paging.common.MovieListPagingSourceData

data class SearchPagingSourceData(
    val searchKeyword: String
) : MovieListPagingSourceData