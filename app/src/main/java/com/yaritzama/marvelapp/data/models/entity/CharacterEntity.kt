package com.yaritzama.marvelapp.data.models.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo
    val name: String? = "",
    @ColumnInfo
    val description: String? = "",
    @ColumnInfo(name = "image_url")
    val imageUrl: String? = "",
    @ColumnInfo(name = "detail_url")
    val detailUrl: String
)
