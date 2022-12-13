package com.yaritzama.marvelapp.domain.repository

import com.yaritzama.marvelapp.domain.model.CharacterModel

interface MarvelRepository {
    suspend fun getCharacterList(): List<CharacterModel>
}