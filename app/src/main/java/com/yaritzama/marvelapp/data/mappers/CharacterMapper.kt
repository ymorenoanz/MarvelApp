package com.yaritzama.marvelapp.data.mappers

import com.yaritzama.marvelapp.data.models.character.ResultResponse
import com.yaritzama.marvelapp.data.models.comicsresponse.ResultComic
import com.yaritzama.marvelapp.domain.model.CharacterModel
import com.yaritzama.marvelapp.domain.model.ComicModel

fun ResultResponse.toDomain(): CharacterModel{
    return CharacterModel(
        id = this.id,
        name = this.name,
        imageUrl = this.thumbnail?.path,
        extensionImage = this.thumbnail?.extension
    )
}

fun ResultComic.toDomain(): ComicModel{
    return ComicModel(
        name = this.title,
        resourceURI = this.resourceURI,
        pathImage = this.thumbnail?.path,
        extensionImage = this.thumbnail?.extension
    )
}