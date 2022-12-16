package com.yaritzama.marvelapp.core.security

import com.yaritzama.marvelapp.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val ts = Timestamp(System.currentTimeMillis()).time.toString()
        val hash = getHash(ts)
        val request = chain.request()
        val newUrl = addQueryParameters(request.url, ts, hash)
        val newRequest = request.newBuilder().url(newUrl).build()
        return chain.proceed(newRequest)
    }

    private fun getHash(ts: String): String {
        val input = "$ts${BuildConfig.PRIVATE_KEY}${BuildConfig.API_KEY}"
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray()))
            .toString(16)
            .padStart(32, '0')
    }

    private fun addQueryParameters(url: HttpUrl, ts: String, hash: String): HttpUrl {
        return url.newBuilder()
            .addQueryParameter("ts", ts)
            .addQueryParameter("hash", hash)
            .addQueryParameter("apikey", BuildConfig.API_KEY)
            .addQueryParameter("limit","100")
            .build()
    }
}


