package com.yaritzama.marvelapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.yaritzama.marvelapp.data.models.entity.ComicEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ComicDAO {

    @Insert
    suspend fun insert(list: List<ComicEntity>)

    @Query("DELETE FROM comic")
    suspend fun deleteAll()

    @Query("SELECT * FROM comic WHERE character_id =:characterId")
    fun getAll(characterId: Int): List<ComicEntity>

}