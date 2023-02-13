package com.rkukac.movieapp.domain.model

import com.rkukac.movieapp.data.network.model.SearchMoviesResponse

data class DomainSearchMoviesResponse(
    val page: Int,
    val movies: List<DomainMovie>,
    val totalResults: Int,
    val totalPages: Int
)

fun SearchMoviesResponse.toDomainSearchMoviesResponse() = DomainSearchMoviesResponse(
    page = page,
    movies = movies.map { it.toDomainMovie() },
    totalResults = totalResults,
    totalPages = totalPages
)