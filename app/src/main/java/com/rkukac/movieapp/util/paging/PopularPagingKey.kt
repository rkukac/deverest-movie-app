package com.rkukac.movieapp.util.paging

import com.rkukac.movieapp.util.paging.common.MovieListPagingKey

data class PopularPagingKey(
    override val page: Int
) : MovieListPagingKey