package com.yaritzama.marvelapp.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yaritzama.marvelapp.core.di.qualifier.IODispatcher
import com.yaritzama.marvelapp.core.di.qualifier.MainDispatcher
import com.yaritzama.marvelapp.domain.model.SeriesModel
import com.yaritzama.marvelapp.domain.repository.MarvelRepository
import com.yaritzama.marvelapp.presentation.ui.state.StateUI
import com.yaritzama.marvelapp.core.utils.validateResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher,
    private val savedStateHandle: SavedStateHandle,
    private val repository: MarvelRepository
) : ViewModel() {

    private val _stateUI = mutableStateOf(StateUI<List<SeriesModel>>())
    val stateUI: State<StateUI<List<SeriesModel>>> get() = _stateUI

    init {
        getSeriesList()
    }

    fun setCharacterId(id: Int) {
        savedStateHandle["characterId"] = id
    }

    private fun getSeriesList() {
        _stateUI.value = _stateUI.value.copy(isLoading = true)
        val characterId: Int = checkNotNull(savedStateHandle["characterId"])
        viewModelScope.launch(ioDispatcher + CoroutineExceptionHandler { _, throwable ->
            print(throwable.message)
        })
        {
            repository.getSeriesList(characterId)
                .validateResult(currentState = _stateUI, dispatcher = mainDispatcher) { state ->
                    _stateUI.value = state
                }
        }
    }

    fun setErrorNull() {
        _stateUI.value = _stateUI.value.copy(error = null)
    }

}


