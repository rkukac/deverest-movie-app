package com.rkukac.movieapp.util.paging

import com.rkukac.movieapp.util.paging.common.MovieListPagingKey

data class SearchPagingKey(
    override val page: Int,
    val searchKeyword: String,
) : MovieListPagingKey