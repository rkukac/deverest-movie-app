package com.rkukac.movieapp.domain

import com.rkukac.movieapp.data.network.MovieNetworkDataSource
import com.rkukac.movieapp.data.network.model.getContentOrThrow
import com.rkukac.movieapp.domain.model.*
import com.rkukac.movieapp.util.MovieHelper
import com.rkukac.movieapp.util.paging.PagingHelper
import com.rkukac.movieapp.util.paging.PagingHelperListener
import com.rkukac.movieapp.util.paging.common.PagingSourceListener
import java.math.BigDecimal
import javax.inject.Inject

class MovieInteractor @Inject constructor(
    private val networkDataSource: MovieNetworkDataSource,
    private val movieHelper: MovieHelper,
    private val pagingHelper: PagingHelper,
) : PagingHelperListener {

    private val apiKey = "555dd34b51d2f5b7f9fdb39e04986933"

    private val imageFormatterBlock: (String?) -> String? = { image ->
        movieHelper.getFormattedImage(image = image)
    }

    private val amountFormatterBlock: (BigDecimal?) -> String = { amount ->
        movieHelper.getFormattedAmount(amount = amount)
    }

    //region Search
    fun getSearchErrorStateModel(): DomainStateModel = movieHelper.getSearchErrorStateModel()

    fun getSearchEmptyStateModel(): DomainStateModel = movieHelper.getSearchEmptyStateModel()

    private suspend fun searchMovies(searchKeyword: String, page: Int): DomainSearchMoviesResponse {
        val searchResult = networkDataSource.searchMovies(
            apiKey = apiKey,
            searchKeyword = searchKeyword,
            page = page
        ).getContentOrThrow()

        return searchResult.copy(movies = getBudgetUpdatedMovieList(searchResult.movies))
    }

    private suspend fun getBudgetUpdatedMovieList(movies: List<DomainMovie>): List<DomainMovie> {
        return movies.map {
            getMovieDetails(it.id).toDomainMovie(
                imageFormatterBlock = imageFormatterBlock,
                amountFormatterBlock = amountFormatterBlock
            )
        }
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

    //region Details
    fun getDetailsErrorStateModel(): DomainStateModel = movieHelper.getDetailsErrorStateModel()

    suspend fun getMovieDetails(movieId: Int): DomainMovieDetails {
        return networkDataSource.getMovieDetails(
            apiKey = apiKey,
            movieId = movieId
        ).getContentOrThrow()
    }
    //endregion

    //region Popular
    private suspend fun getPopularMovies(page: Int): DomainGetPopularMoviesResponse {
        return networkDataSource.getPopularMovies(
            apiKey = apiKey,
            page = page
        ).getContentOrThrow()
    }
    //endregion

    //region PagingHelperListener
    override suspend fun pagingSearchMovies(
        searchKeyword: String,
        page: Int
    ): DomainSearchMoviesResponse = searchMovies(searchKeyword = searchKeyword, page = page)
    //endregion
}