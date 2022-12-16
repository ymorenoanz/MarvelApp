package com.yaritzama.marvelapp.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yaritzama.marvelapp.domain.model.SeriesModel
import com.yaritzama.marvelapp.domain.repository.MarvelRepository
import com.yaritzama.marvelapp.utils.validateResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: MarvelRepository): ViewModel() {


    private val _seriesList = mutableStateListOf<SeriesModel>()
    val seriesList: SnapshotStateList<SeriesModel>
        get() = _seriesList

    private val  _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> get() = _error


    init {
        getSeriesList()
    }

    fun setCharacterId(id: Int){
        savedStateHandle["characterId"] = id
    }

    private fun getSeriesList(){
        val characterId: Int = checkNotNull(savedStateHandle["characterId"])
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            _error.value = throwable.message.toString()
            _isLoading.value = false
        })
        {
            val result = repository.getSeriesList(characterId)
            result.validateResult(Dispatchers.Main,::onSuccess,::onError)
            _isLoading.value = false
        }

    }

    private fun onSuccess(list: List<SeriesModel>){
        _seriesList.clear()
        _seriesList.addAll(list)
        _error.value = null
    }

    private fun onError(error: String){
        _error.value = error
    }

}


