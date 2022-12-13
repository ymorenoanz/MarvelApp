package com.yaritzama.marvelapp.data.mappers

import com.yaritzama.marvelapp.data.models.ResultResponse
import com.yaritzama.marvelapp.domain.model.CharacterModel

fun ResultResponse.toDomain(): CharacterModel{
    return CharacterModel(
        id = this.id,
        name = this.name)
}