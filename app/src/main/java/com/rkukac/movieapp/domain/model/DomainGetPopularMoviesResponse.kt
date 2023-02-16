package com.rkukac.movieapp.domain.model

import com.rkukac.movieapp.data.network.model.GetPopularMoviesResponse

data class DomainGetPopularMoviesResponse(
    override val page: Int,
    override val movies: List<DomainMovie>,
    val totalResults: Int,
    val totalPages: Int
) : DomainMovieListBase

fun GetPopularMoviesResponse.toDomainGetPopularMoviesResponse() = DomainGetPopularMoviesResponse(
    page = page,
    movies = movies.map { it.toDomainMovie() },
    totalResults = totalResults,
    totalPages = totalPages
)