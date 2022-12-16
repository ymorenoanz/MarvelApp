package com.yaritzama.marvelapp.domain.model

data class CharacterModel(
    val id: Int? = 0,
    val name: String? = "",
    val description: String = "",
    val imageUrl: String? = "",
    val detailUrl: String
)
