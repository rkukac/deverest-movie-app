package com.rkukac.movieapp.util.network

import com.rkukac.movieapp.data.network.model.MovieError
import com.rkukac.movieapp.data.network.model.MovieErrorResponse
import com.rkukac.movieapp.data.network.model.MovieResponse
import com.rkukac.movieapp.data.network.model.MovieSuccess
import com.rkukac.movieapp.util.moshi
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

const val UNKNOWN_NETWORK_ERROR_CODE = 0

inline fun <reified T> executeRequest(
    requestBlock: () -> Response<T>
): MovieResponse<T> {
    return try {
        val response = requestBlock()
        return if (response.isSuccessful.not()) tryParseError(response)
        else MovieSuccess(response.body() ?: Unit as T)
    } catch (e: IOException) {
        Timber.d(e, "Network request failed!")
        MovieError(UNKNOWN_NETWORK_ERROR_CODE)
    }
}

@PublishedApi
internal fun <T> tryParseError(response: Response<*>): MovieError<T> {
    val errorBody = response.errorBody()?.string() ?: return MovieError(response.code())

    val parseBody = parseError(errorBody)
    return if (parseBody?.message.isNullOrEmpty().not()) {
        MovieError(parseBody)
    } else {
        MovieError(response.code())
    }
}

private fun parseError(errorBody: String): MovieErrorResponse? {
    return try {
        Timber.d("Try parse body as MovieErrorResponse")
        return moshi.adapter(MovieErrorResponse::class.java).fromJson(errorBody)
    } catch (e: Exception) {
        Timber.d(e, "Cannot parse MovieErrorResponse error body")
        null
    }
}