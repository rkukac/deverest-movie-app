package com.rkukac.movieapp.data.network

import com.rkukac.movieapp.data.network.api.MovieApi
import com.rkukac.movieapp.util.network.executeRequest
import javax.inject.Inject

class MovieNetworkDataSource @Inject constructor(
    private val movieApi: MovieApi
) {

    suspend fun searchMovies(apiKey: String, searchKeyword: String, page: Int) = executeRequest {
        movieApi.searchMovies(apiKey = apiKey, searchKeyword = searchKeyword, page = page)
    }
}