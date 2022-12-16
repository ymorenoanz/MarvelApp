package com.yaritzama.marvelapp.di

import android.content.Context
import androidx.room.Room
import com.yaritzama.marvelapp.data.database.AppDatabase
import com.yaritzama.marvelapp.data.database.dao.CharacterDAO
import com.yaritzama.marvelapp.data.database.dao.ComicDAO
import com.yaritzama.marvelapp.data.database.dao.SeriesDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context,AppDatabase::class.java,"Marvel.db").build()

    @Singleton
    @Provides
    fun provideCharacterDAO(appDataBase: AppDatabase): CharacterDAO =
        appDataBase.getCharacterDAO()

    @Singleton
    @Provides
    fun provideComicDAO(appDataBase: AppDatabase): ComicDAO =
        appDataBase.getComicDAO()

    @Singleton
    @Provides
    fun provideSeriesDAO(appDataBase: AppDatabase): SeriesDAO =
        appDataBase.getSeriesDAO()

}