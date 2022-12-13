package com.yaritzama.marvelapp.di

import com.yaritzama.marvelapp.BuildConfig
import com.yaritzama.marvelapp.data.api.MarvelAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesOkHttpClient() = OkHttpClient.Builder()
        .writeTimeout(10L, TimeUnit.SECONDS )
        .readTimeout(10L, TimeUnit.SECONDS ).addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        ).build()

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun providesAPI(retrofit: Retrofit): MarvelAPI = retrofit.create(MarvelAPI::class.java)

}