package com.yaritzama.marvelapp.utils

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

fun OkHttpClient.Builder.addAuthInterceptor(): OkHttpClient.Builder{
    addInterceptor(AuthInterceptor())
    return this
}

fun OkHttpClient.Builder.addLoggerInterceptor(): OkHttpClient.Builder{
    addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    })
    return this
}