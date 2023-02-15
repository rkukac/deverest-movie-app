package com.rkukac.movieapp.domain

import com.rkukac.movieapp.data.network.MovieNetworkDataSource
import com.rkukac.movieapp.data.network.model.getContentOrThrow
import com.rkukac.movieapp.domain.model.DomainMovieDetails
import com.rkukac.movieapp.domain.model.DomainSearchMoviesResponse
import com.rkukac.movieapp.domain.model.DomainStateModel
import com.rkukac.movieapp.util.MovieHelper
import com.rkukac.movieapp.util.paging.PagingHelper
import com.rkukac.movieapp.util.paging.PagingHelperListener
import com.rkukac.movieapp.util.paging.common.PagingSourceListener
import javax.inject.Inject

class MovieInteractor @Inject constructor(
    private val networkDataSource: MovieNetworkDataSource,
    private val movieHelper: MovieHelper,
    private val pagingHelper: PagingHelper,
) : PagingHelperListener {

    private val apiKey = "555dd34b51d2f5b7f9fdb39e04986933"

    //region Search
    fun getSearchErrorStateModel(): DomainStateModel = movieHelper.getSearchErrorStateModel()

    fun getSearchEmptyStateModel(): DomainStateModel = movieHelper.getSearchEmptyStateModel()

    private suspend fun searchMovies(searchKeyword: String, page: Int): DomainSearchMoviesResponse {
        return networkDataSource.searchMovies(
            apiKey = apiKey,
            searchKeyword = searchKeyword,
            page = page
        ).getContentOrThrow()
    }

    fun getEmptySearchKeywordMessage() = movieHelper.getEmptySearchKeywordMessage()

    fun provideSearchPagingSource(
        searchKeyword: String,
        pagingSourceListener: PagingSourceListener
    ) = pagingHelper.provideSearchPagingSource(
        searchKeyword = searchKeyword,
        pagingSourceListener = pagingSourceListener,
        pagingHelperListener = this
    )
    //endregion

    suspend fun getMovieDetails(movieId: Int): DomainMovieDetails {
        return networkDataSource.getMovieDetails(
            apiKey = apiKey,
            movieId = movieId
        ).getContentOrThrow()
    }

    //region PagingHelperListener
    override suspend fun pagingSearchMovies(
        searchKeyword: String,
        page: Int
    ): DomainSearchMoviesResponse = searchMovies(searchKeyword = searchKeyword, page = page)
    //endregion
}