package com.rkukac.movieapp.domain.model

import com.rkukac.movieapp.data.network.model.SearchMoviesResponse

data class DomainSearchMoviesResponse(
    override val page: Int,
    override val movies: List<DomainMovie>,
    val totalResults: Int,
    val totalPages: Int
) : DomainMovieListBase

fun SearchMoviesResponse.toDomainSearchMoviesResponse(
    imageFormatterBlock: (String?) -> String?
) = DomainSearchMoviesResponse(
    page = page,
    movies = movies.map { it.toDomainMovie(imageFormatterBlock = imageFormatterBlock) },
    totalResults = totalResults,
    totalPages = totalPages
)