package com.yaritzama.marvelapp.core.utils

import retrofit2.Response

suspend fun <T, R> Response<T>.validateResponse(
    success: suspend (T?) -> R,
    errorResponse: (message: Exception) -> R
): R {
    try {
        if (isSuccessful) return success(body())
        return errorResponse(Exception("Error"))
    } catch (e: Exception) {
        return errorResponse(e)
    }
}