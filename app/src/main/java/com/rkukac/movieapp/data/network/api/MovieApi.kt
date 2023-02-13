package com.rkukac.movieapp.data.network.api

import com.rkukac.movieapp.data.network.model.SearchMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    /**
     * Search for movies.
     *
     * Responses:
     *  - 200: Successful operation
     *  - 401: Invalid
     *  - 404: Not available
     *
     * @param apiKey The API key.
     * @param searchKeyword The search query keyword.
     * @param page The selected page number.
     * @return [SearchMoviesResponse]
     */
    @GET("/search/movie")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String,
        @Query("query") searchKeyword: String,
        @Query("page") page: Int
    ): Response<SearchMoviesResponse>
}