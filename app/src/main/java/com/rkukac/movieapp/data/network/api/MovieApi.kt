package com.rkukac.movieapp.data.network.api

import com.rkukac.movieapp.data.network.model.GetPopularMoviesResponse
import com.rkukac.movieapp.data.network.model.MovieDetails
import com.rkukac.movieapp.data.network.model.SearchMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
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
    @GET("search/movie")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String,
        @Query("query") searchKeyword: String,
        @Query("page") page: Int
    ): Response<SearchMoviesResponse>

    /**
     * Get movie details.
     *
     * Responses:
     *  - 200: Successful operation
     *  - 401: Invalid
     *  - 404: Not available
     *
     * @param movieId The ID of the movie.
     * @param apiKey The API key.
     * @return [MovieDetails]
     */
    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<MovieDetails>

    /**
     * Get a list of the current popular movies on TMDB. This list updates daily.
     *
     * Responses:
     *  - 200: Successful operation
     *  - 401: Invalid
     *  - 404: Not available
     *
     * @param apiKey The API key.
     * @param page The selected page number.
     * @return [GetPopularMoviesResponse]
     */
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<GetPopularMoviesResponse>
}