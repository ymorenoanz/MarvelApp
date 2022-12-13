package com.yaritzama.marvelapp.data.repository

import com.yaritzama.marvelapp.BuildConfig
import com.yaritzama.marvelapp.data.api.MarvelAPI
import com.yaritzama.marvelapp.data.mappers.toDomain
import com.yaritzama.marvelapp.domain.model.CharacterModel
import com.yaritzama.marvelapp.domain.repository.MarvelRepository
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(
    private val api: MarvelAPI
): MarvelRepository {

    override suspend fun getCharacterList(): List<CharacterModel> {
        val response = api.getCharacterList(BuildConfig.API_KEY, "1234", hash = BuildConfig.HASH)
        return response.body()?.results?.map{
            it.toDomain() ?: CharacterModel()
        } ?: emptyList()
    }
}