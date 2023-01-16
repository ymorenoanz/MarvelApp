package com.yaritzama.marvelapp.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yaritzama.marvelapp.core.utils.*
import com.yaritzama.marvelapp.domain.model.ComicModel
import com.yaritzama.marvelapp.domain.repository.MarvelRepository
import com.yaritzama.marvelapp.presentation.ui.state.StateUI
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import org.koin.java.KoinJavaComponent.inject


class ComicsViewModel(
    private val mainDispatcher: CoroutineDispatcher,
    private val ioDispatcher: CoroutineDispatcher,
    private val savedStateHandle: SavedStateHandle,
    private val repository: MarvelRepository
) : ViewModel() {


    private val _stateUI = mutableStateOf(StateUI<List<ComicModel>>())
    val stateUI: State<StateUI<List<ComicModel>>> get() = _stateUI

    init {
        getComicList()
    }

    fun setCharacterId(id: Int) {
        savedStateHandle["characterId"] = id
    }

    private fun getComicList() {
        _stateUI.value = _stateUI.value.copy(isLoading = true)
        val characterId: Int = checkNotNull(savedStateHandle["characterId"])
        viewModelScope.launch(ioDispatcher + CoroutineExceptionHandler { _, throwable ->
            println(throwable.message)
        }) {
            repository.getComicList(characterId)
                .validateResult(currentState = _stateUI, dispatcher = mainDispatcher) { state ->
                    _stateUI.value = state
                }
        }
    }

    fun setErrorNull() {
        _stateUI.value = _stateUI.value.copy(error = null)
    }


}