package com.yaritzama.marvelapp.data.mappers

import com.yaritzama.marvelapp.data.models.character.ResultResponse
import com.yaritzama.marvelapp.data.models.entity.CharacterEntity
import com.yaritzama.marvelapp.domain.model.CharacterModel


fun List<ResultResponse>.toDomain(): List<CharacterModel> =
    map {  CharacterModel(
        id = it.id,
        name = it.name,
        imageUrl = "${it.thumbnail?.path?.replace("https","https")}.${it.thumbnail?.extension}",
        detailUrl = it.urls?.find { url ->  url.type == "detail"}?.url ?: ""
    )}

fun List<CharacterModel>.toEntity(): List<CharacterEntity> =
    map {  CharacterEntity(id = it.id?:0, it.name, it.description , it.imageUrl, it.detailUrl)}

@JvmName("toDomainCharacterEntity")
fun List<CharacterEntity>.toDomain(): List<CharacterModel> =
    map{CharacterModel(id = it.id, it.name, it.description?:"" ,it.imageUrl, detailUrl = it.detailUrl)}





/*fun ResultComic.toDomain(characterId: Int): ComicModel{
    return ComicModel(
        name = this.title,
        resourceURI = this.resourceURI,
        characterId = characterId
    )
}*/