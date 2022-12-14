package com.yaritzama.marvelapp.data.api

import com.yaritzama.marvelapp.data.models.CharacterResponse
import com.yaritzama.marvelapp.data.models.Data
import com.yaritzama.marvelapp.domain.model.CharacterModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelAPI {

    @GET("characters")
    suspend fun getCharacterList(
        @Query("apikey" ) apikey: String,
        @Query("ts") ts: String,
        @Query("hash") hash: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int): Response<CharacterResponse>
}