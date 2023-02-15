package com.rkukac.movieapp.util.paging

import com.rkukac.movieapp.domain.model.DomainSearchMoviesResponse

interface PagingHelperListener {

    suspend fun pagingSearchMovies(searchKeyword: String, page: Int): DomainSearchMoviesResponse
}