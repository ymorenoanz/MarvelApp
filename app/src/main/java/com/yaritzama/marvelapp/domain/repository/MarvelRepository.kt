package com.yaritzama.marvelapp.domain.repository

import com.yaritzama.marvelapp.domain.model.BaseResult
import com.yaritzama.marvelapp.domain.model.CharacterModel
import com.yaritzama.marvelapp.domain.model.ComicModel
import com.yaritzama.marvelapp.domain.model.SeriesModel

interface MarvelRepository {
    suspend fun getCharacterList(needsUpdate: Boolean): BaseResult<List<CharacterModel>>
    suspend fun getComicList(characterId: Int): BaseResult<List<ComicModel>>
    suspend fun getSeriesList(characterId: Int): BaseResult<List<SeriesModel>>
}