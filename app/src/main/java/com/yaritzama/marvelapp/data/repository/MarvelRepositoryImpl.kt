package com.yaritzama.marvelapp.data.repository

import com.yaritzama.marvelapp.data.api.MarvelAPI
import com.yaritzama.marvelapp.data.database.dao.CharacterDAO
import com.yaritzama.marvelapp.data.database.dao.ComicDAO
import com.yaritzama.marvelapp.data.database.dao.SeriesDAO
import com.yaritzama.marvelapp.data.mappers.*
import com.yaritzama.marvelapp.data.models.character.CharacterResponse
import com.yaritzama.marvelapp.domain.model.BaseResult
import com.yaritzama.marvelapp.domain.model.CharacterModel
import com.yaritzama.marvelapp.domain.model.ComicModel
import com.yaritzama.marvelapp.domain.model.SeriesModel
import com.yaritzama.marvelapp.domain.repository.MarvelRepository
import com.yaritzama.marvelapp.utils.validateResponse
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(
    private val api: MarvelAPI,
    private val characterDAO: CharacterDAO,
    private val comicDAO: ComicDAO,
    private val seriesDAO: SeriesDAO,
) : MarvelRepository {

    override suspend fun getCharacterList(needsUpdate: Boolean): BaseResult<List<CharacterModel>> {
        val  forceUpdate = characterDAO.getCount() == 0
        return if ( needsUpdate || forceUpdate) {

            api.getCharacterList().validateResponse(
                { characterResponse ->

                    characterDAO.deleteAll()
                    comicDAO.deleteAll()
                    seriesDAO.deleteAll()

                    val charactersModel = characterResponse?.data?.results?.toDomain()

                    charactersModel?.toEntity()?.let { it ->
                       characterDAO.insert(it)
                    }

                    characterResponse?.let {
                        saveComicsFromCharacterResponse(it)
                        saveSeriesFromCharacterResponse(it)
                    }

                    BaseResult.Success(characterDAO.getAll().toDomain())
                }, { throwable ->
                    BaseResult.Error(throwable.toString())
                }
            )

        } else  BaseResult.Success(characterDAO.getAll().toDomain())


    }

    override suspend fun getComicList(characterId: Int): BaseResult<List<ComicModel>> =
        BaseResult.Success(comicDAO.getAll(characterId).toDomain())

    override suspend fun getSeriesList(characterId: Int): BaseResult<List<SeriesModel>> =
        BaseResult.Success(seriesDAO.getAll(characterId).toDomain())

    private suspend fun saveComicsFromCharacterResponse(characterResponse: CharacterResponse){
        val comicsModelList = arrayListOf<ComicModel>()
        characterResponse.data?.results?.forEach { characters ->
            characters.comics?.items?.toDomainComic(characters.id?:0)
                ?.let { comicsModelList.addAll(it) }
        }
        comicDAO.insert(comicsModelList.toEntityComic())
    }

    private suspend fun saveSeriesFromCharacterResponse(characterResponse: CharacterResponse){
        val comicsModelList = arrayListOf<SeriesModel>()
        characterResponse.data?.results?.forEach { characters ->
            characters.comics?.items?.toDomainSeries(characters.id?:0)
                ?.let { comicsModelList.addAll(it) }
        }
        seriesDAO.insert(comicsModelList.toSeriesEntity())
    }

}


