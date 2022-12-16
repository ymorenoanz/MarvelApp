package com.yaritzama.marvelapp.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yaritzama.marvelapp.domain.model.BaseResult
import com.yaritzama.marvelapp.domain.model.ComicModel
import com.yaritzama.marvelapp.domain.repository.MarvelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ComicsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: MarvelRepository
) : ViewModel() {

    private val _comicList = mutableStateListOf<ComicModel>()
    val comicList: SnapshotStateList<ComicModel>
        get() = _comicList

    private val  _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> get() = _error


    init {
        getComicList()
    }

    fun setCharacterId(id: Int){
        savedStateHandle["characterId"] = id
    }

    private fun getComicList(){
        val characterId: Int = checkNotNull(savedStateHandle["characterId"])
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->  }){
            when(val result = repository.getComicList(characterId)){
                is BaseResult.Success -> {
                    withContext(Dispatchers.Main){
                    _comicList.clear()
                    _comicList.addAll(result.data)}
                }
                is BaseResult.Error -> {}
            }
        }
    }


}