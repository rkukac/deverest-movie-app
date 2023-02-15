package com.rkukac.movieapp.domain.model

interface DomainMovieListBase {

    companion object {
        const val DEFAULT_PAGE = 1
    }

    val page: Int

    val movies: List<DomainMovie>
}