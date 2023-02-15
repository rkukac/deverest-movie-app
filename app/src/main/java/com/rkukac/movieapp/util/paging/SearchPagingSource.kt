package com.rkukac.movieapp.util.paging

import com.rkukac.movieapp.domain.model.DomainSearchMoviesResponse
import com.rkukac.movieapp.util.paging.common.MovieListPagingSource
import javax.inject.Inject

class SearchPagingSource @Inject constructor() :
    MovieListPagingSource<SearchPagingKey, DomainSearchMoviesResponse>() {

    override suspend fun loadDomainValue(key: SearchPagingKey): DomainSearchMoviesResponse? =
        pagingHelperListener?.pagingSearchMovies(
            searchKeyword = key.searchKeyword,
            page = key.page
        )

    override fun getNextKey(
        key: SearchPagingKey,
        response: DomainSearchMoviesResponse
    ): SearchPagingKey? {
        return if (response.movies.isEmpty()) {
            null
        } else {
            SearchPagingKey(
                searchKeyword = key.searchKeyword,
                page = key.page.inc()
            )
        }
    }
}