package com.yaritzama.marvelapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yaritzama.marvelapp.data.database.dao.CharacterDAO
import com.yaritzama.marvelapp.data.database.dao.ComicDAO
import com.yaritzama.marvelapp.data.database.dao.SeriesDAO
import com.yaritzama.marvelapp.data.models.entity.CharacterEntity
import com.yaritzama.marvelapp.data.models.entity.ComicEntity
import com.yaritzama.marvelapp.data.models.entity.SeriesEntity

@Database(
    entities = [CharacterEntity::class, ComicEntity::class, SeriesEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getCharacterDAO(): CharacterDAO
    abstract fun getComicDAO(): ComicDAO
    abstract fun getSeriesDAO(): SeriesDAO
}