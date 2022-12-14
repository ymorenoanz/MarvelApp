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
    savedStateHandle: SavedStateHandle,
    private val repo: MarvelRepository
): ViewModel() {

    private val _characterList = mutableStateListOf<CharacterModel>()
    val characterList: SnapshotStateList<CharacterModel>
            get() = _characterList

    private val _comicList = mutableStateListOf<ComicModel>()
    val comicList: SnapshotStateList<ComicModel>
        get() = _comicList

    //val characterId: Int = checkNotNull(savedStateHandle["characterId"])
    //private val characterInfo: Flow<CharacterModel> = repo.getComicList(characterId = characterId)

    init{
        getCharacterList()
        getComicList()
    }

    private fun getCharacterList(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repo.getCharacterList()
            _characterList.addAll(response)
        }
    }

    private fun getComicList(){
        viewModelScope.launch(Dispatchers.IO){
            val responseCharacter = repo.getCharacterList()
            val response = repo.getComicList(1011334)
            _comicList.addAll(response)
        }
    }

}