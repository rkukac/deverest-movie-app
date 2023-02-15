package com.rkukac.movieapp.data.network

import com.rkukac.movieapp.data.network.api.MovieApi
import com.rkukac.movieapp.data.network.model.MovieDetails
import com.rkukac.movieapp.data.network.model.SearchMoviesResponse
import com.rkukac.movieapp.data.network.model.map
import com.rkukac.movieapp.domain.model.toDomainMovieDetails
import com.rkukac.movieapp.domain.model.toDomainSearchMoviesResponse
import com.rkukac.movieapp.util.network.executeRequest
import javax.inject.Inject

class MovieNetworkDataSource @Inject constructor(
    private val movieApi: MovieApi
) {

    suspend fun searchMovies(
        apiKey: String,
        searchKeyword: String,
        page: Int
    ) = executeRequest {
        movieApi.searchMovies(apiKey = apiKey, searchKeyword = searchKeyword, page = page)
    }.map(SearchMoviesResponse::toDomainSearchMoviesResponse)

    suspend fun getMovieDetails(apiKey: String, movieId: Int) = executeRequest {
        movieApi.getMovieDetails(movieId = movieId, apiKey = apiKey)
    }.map(MovieDetails::toDomainMovieDetails)
}