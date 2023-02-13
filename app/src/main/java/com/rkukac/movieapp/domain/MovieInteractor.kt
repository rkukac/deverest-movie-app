package com.rkukac.movieapp.domain

import com.rkukac.movieapp.data.network.MovieNetworkDataSource
import com.rkukac.movieapp.data.network.model.getContentOrThrow
import com.rkukac.movieapp.domain.model.DomainMovieDetails
import com.rkukac.movieapp.domain.model.DomainSearchMoviesResponse
import javax.inject.Inject

class MovieInteractor @Inject constructor(
    private val networkDataSource: MovieNetworkDataSource
) {

    private val apiKey = "555dd34b51d2f5b7f9fdb39e04986933"

    suspend fun searchMovies(searchKeyword: String, page: Int): DomainSearchMoviesResponse {
        return networkDataSource.searchMovies(
            apiKey = apiKey,
            searchKeyword = searchKeyword,
            page = page
        ).getContentOrThrow()
    }

    suspend fun getMovieDetails(movieId: Int): DomainMovieDetails {
        return networkDataSource.getMovieDetails(
            apiKey = apiKey,
            movieId = movieId
        ).getContentOrThrow()
    }
}