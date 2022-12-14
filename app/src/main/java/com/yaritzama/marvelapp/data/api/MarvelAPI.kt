package com.yaritzama.marvelapp.data.api

import com.yaritzama.marvelapp.data.models.character.CharacterResponse
import com.yaritzama.marvelapp.data.models.comicsresponse.ComicResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelAPI {

    @GET("characters")
    suspend fun getCharacterList(
        @Query("apikey" ) apikey: String,
        @Query("ts") ts: String,
        @Query("hash") hash: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int): Response<CharacterResponse>

    //PENDING
    @GET("characters/{characterId}/comics")
    suspend fun getComicsList(
        @Path("characterId") characterId: Int,
        @Query("apikey" ) apikey: String,
        @Query("ts") ts: String,
        @Query("hash") hash: String): Response<ComicResponse>
}