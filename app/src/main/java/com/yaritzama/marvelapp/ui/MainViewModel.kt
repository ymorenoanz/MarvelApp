package com.yaritzama.marvelapp.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yaritzama.marvelapp.domain.model.CharacterModel
import com.yaritzama.marvelapp.domain.repository.MarvelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: MarvelRepository
): ViewModel() {

    private val _characterList = mutableStateListOf<CharacterModel>()
    val characterList: SnapshotStateList<CharacterModel>
            get() = _characterList

    val character = mutableStateOf<List<CharacterModel>>(listOf())

    init{
        getCharacterList()
    }

    private fun getCharacterList(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repo.getCharacterList()
            _characterList.addAll(response)
        }
    }

}