package com.yaritzama.marvelapp.domain.repository

import com.yaritzama.marvelapp.domain.model.CharacterModel
import com.yaritzama.marvelapp.domain.model.ComicModel

interface MarvelRepository {
    suspend fun getCharacterList(): List<CharacterModel>
    suspend fun getComicList(characterId: Int): List<ComicModel>
}