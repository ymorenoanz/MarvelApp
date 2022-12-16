package com.yaritzama.marvelapp.di

import com.yaritzama.marvelapp.BuildConfig
import com.yaritzama.marvelapp.data.api.MarvelAPI
import com.yaritzama.marvelapp.utils.addAuthInterceptor
import com.yaritzama.marvelapp.utils.addLoggerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesOkHttpClient() = OkHttpClient.Builder()
        .addAuthInterceptor()
        .addLoggerInterceptor()
        .build()

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun providesAPI(retrofit: Retrofit): MarvelAPI = retrofit.create(MarvelAPI::class.java)

}