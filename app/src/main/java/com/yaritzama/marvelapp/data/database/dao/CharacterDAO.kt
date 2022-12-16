package com.yaritzama.marvelapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.yaritzama.marvelapp.data.models.entity.CharacterEntity

@Dao
interface CharacterDAO {

    @Insert
    suspend fun insert(list: List<CharacterEntity>)

    @Query("DELETE FROM character")
    suspend fun deleteAll()

    @Query("SELECT * FROM character")
    suspend fun getAll(): List<CharacterEntity>

    @Query("SELECT COUNT(*) FROM character")
    suspend fun getCount(): Int

}