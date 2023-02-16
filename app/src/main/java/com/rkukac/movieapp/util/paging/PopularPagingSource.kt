package com.rkukac.movieapp.util.paging

import com.rkukac.movieapp.domain.model.DomainGetPopularMoviesResponse
import com.rkukac.movieapp.util.paging.common.MovieListPagingSource
import javax.inject.Inject

class PopularPagingSource @Inject constructor() :
    MovieListPagingSource<PopularPagingKey, DomainGetPopularMoviesResponse>() {

    override suspend fun loadDomainValue(key: PopularPagingKey): DomainGetPopularMoviesResponse? =
        pagingHelperListener?.pagingGetPopularMovies(
            page = key.page
        )

    override fun getNextKey(
        key: PopularPagingKey,
        response: DomainGetPopularMoviesResponse
    ): PopularPagingKey? {
        return if (response.movies.isEmpty()) {
            null
        } else {
            PopularPagingKey(
                page = key.page.inc()
            )
        }
    }
}