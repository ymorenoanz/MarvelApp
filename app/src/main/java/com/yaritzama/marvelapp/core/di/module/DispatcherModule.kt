package com.yaritzama.marvelapp.core.di.module

import com.yaritzama.marvelapp.core.di.qualifier.IODispatcher
import com.yaritzama.marvelapp.core.di.qualifier.MainDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @Provides
    @Singleton
    @MainDispatcher
    fun provideDispatcherMain(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    @Singleton
    @IODispatcher
    fun provideDispatcherIO(): CoroutineDispatcher = Dispatchers.IO

}