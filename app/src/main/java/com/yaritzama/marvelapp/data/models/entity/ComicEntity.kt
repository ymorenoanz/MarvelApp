package com.yaritzama.marvelapp.data.models.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity( tableName = "comic")
data class ComicEntity(
    @PrimaryKey( autoGenerate = true )
    val id: Long = 0,
    @ColumnInfo
    val name: String? = "",
    @ColumnInfo(name = "resource_URI")
    val resourceURI: String? = "",
    @ColumnInfo(name = "character_id")
    val characterId: Int
)
