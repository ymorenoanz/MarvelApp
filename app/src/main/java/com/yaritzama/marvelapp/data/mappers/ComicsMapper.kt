package com.yaritzama.marvelapp.data.mappers

import com.yaritzama.marvelapp.data.models.character.Item
import com.yaritzama.marvelapp.data.models.entity.ComicEntity
import com.yaritzama.marvelapp.domain.model.ComicModel



fun List<ComicEntity>.toDomain(): List<ComicModel> =
    map{ ComicModel(name = it.name,  it.resourceURI?:"" ,it.characterId) }

fun List<Item>.toDomainComic(characterId: Int): List<ComicModel> =
    map { ComicModel(it.name,it.resourceURI,characterId) }

fun List<ComicModel>.toEntityComic(): List<ComicEntity> =
    map{ ComicEntity(name = it.name, resourceURI = it.resourceURI, characterId = it.characterId)}