package com.yaritzama.marvelapp.data.mappers

import com.yaritzama.marvelapp.data.models.character.Item
import com.yaritzama.marvelapp.data.models.entity.SeriesEntity
import com.yaritzama.marvelapp.domain.model.SeriesModel



fun List<Item>.toDomainSeries(characterId: Int): List<SeriesModel> =
    map { SeriesModel(it.name,it.resourceURI,characterId) }

fun List<SeriesEntity>.toDomain(): List<SeriesModel> =
    map{ SeriesModel(name = it.name,  it.resourceURI?:"" ,it.characterId) }


fun List<SeriesModel>.toSeriesEntity(): List<SeriesEntity> =
    map{ SeriesEntity(name = it.name, resourceURI = it.resourceURI, characterId = it.characterId) }


