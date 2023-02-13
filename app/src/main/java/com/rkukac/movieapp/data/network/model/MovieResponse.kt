package com.rkukac.movieapp.data.network.model

import com.rkukac.movieapp.data.network.model.MovieError.DEFAULT.NO_ERROR_CODE

sealed interface MovieResponse<Content>

class MovieSuccess<Content>(val content: Content) : MovieResponse<Content>
class MovieError<Content> : MovieResponse<Content> {

    object DEFAULT {
        const val NO_ERROR_CODE = -1
    }

    var errorCode: Int = NO_ERROR_CODE
    var error: MovieErrorResponse? = null

    constructor(errorCode: Int) {
        this.errorCode = errorCode
    }

    constructor(error: MovieErrorResponse?) {
        this.error = error
    }
}

val <Content> MovieResponse<Content>.content: Content?
    get() = (this as? MovieSuccess<Content>)?.content

inline fun <Content> MovieResponse<Content>.onSuccess(
    block: (Content) -> Unit
): MovieResponse<Content> {
    if (this is MovieSuccess) {
        block(content)
    }
    return this
}

inline fun <Content> MovieResponse<Content>.onError(
    block: (MovieError<Content>) -> Unit
): MovieResponse<Content> {
    if (this is MovieError) {
        block(this)
    }
    return this
}

@Throws(MovieResponseException::class)
fun <Content> MovieResponse<Content>.getContentOrThrow(): Content {
    return when (this) {
        is MovieSuccess -> content
        is MovieError -> throw MovieResponseException(this)
    }
}

class MovieResponseException(val error: MovieError<*>) : RuntimeException()

inline fun <T, E> MovieResponse<T>.map(block: (T) -> E): MovieResponse<E> {
    return when (this) {
        is MovieError -> {
            if (this.errorCode == NO_ERROR_CODE) {
                val error = MovieError<MovieErrorResponse>(error = error)
                throw MovieResponseException(error)
            } else MovieError(errorCode = errorCode)
        }
        is MovieSuccess -> MovieSuccess(content = block(content))
    }
}