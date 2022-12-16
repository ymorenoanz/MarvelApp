package com.yaritzama.marvelapp.di

import com.yaritzama.marvelapp.data.repository.MarvelRepositoryImpl
import com.yaritzama.marvelapp.domain.repository.MarvelRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providesRepository(impl: MarvelRepositoryImpl): MarvelRepository
}