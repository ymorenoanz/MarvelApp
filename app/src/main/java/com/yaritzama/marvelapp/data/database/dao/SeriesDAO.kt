package com.yaritzama.marvelapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.yaritzama.marvelapp.data.models.entity.SeriesEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface SeriesDAO {

    @Insert
    suspend fun insert(list: List<SeriesEntity>)

    @Query("DELETE FROM series")
    suspend fun deleteAll()

    @Query("SELECT * FROM series WHERE character_id =:characterId")
    suspend fun getAll(characterId: Int): List<SeriesEntity>

}