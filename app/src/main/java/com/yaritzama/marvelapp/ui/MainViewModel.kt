package com.yaritzama.marvelapp.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import com.yaritzama.marvelapp.domain.model.CharacterModel
import com.yaritzama.marvelapp.domain.model.ComicModel
import com.yaritzama.marvelapp.domain.repository.MarvelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repo: MarvelRepository
): ViewModel() {

    private val _characterList = mutableStateListOf<CharacterModel>()
    val characterList: SnapshotStateList<CharacterModel>
            get() = _characterList

    private val _comicList = mutableStateListOf<ComicModel>()
    val comicList: SnapshotStateList<ComicModel>
        get() = _comicList

    //private val series: Flow<CharacterModel> = repo.getComicList(characterId = characterId)

    init{
        getCharacterList()
    }


    fun setCharacterId(id: Int){
        savedStateHandle["characterId"] = id
    }

    private fun getCharacterList(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repo.getCharacterList()
            _characterList.addAll(response)
        }
    }

    fun getComicList(){
        val characterId: Int = checkNotNull(savedStateHandle["characterId"])
        viewModelScope.launch(Dispatchers.IO){
            val response = repo.getComicList(characterId)
            _comicList.addAll(response)
        }
    }

}