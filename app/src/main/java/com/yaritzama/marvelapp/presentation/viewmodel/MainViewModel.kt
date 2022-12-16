package com.yaritzama.marvelapp.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yaritzama.marvelapp.core.di.qualifier.IODispatcher
import com.yaritzama.marvelapp.core.di.qualifier.MainDispatcher
import com.yaritzama.marvelapp.domain.model.CharacterModel
import com.yaritzama.marvelapp.domain.repository.MarvelRepository
import com.yaritzama.marvelapp.presentation.ui.state.StateUI
import com.yaritzama.marvelapp.core.utils.validateResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher,
    private val repo: MarvelRepository
) : ViewModel() {

    private val _stateUI = mutableStateOf(StateUI<List<CharacterModel>>())
    val stateUI: State<StateUI<List<CharacterModel>>> get() = _stateUI

    init {
        getCharacterList()
    }

    fun getCharacterList(needsUpdate: Boolean = false) {
        _stateUI.value = _stateUI.value.copy(isLoading = true)
        viewModelScope.launch(ioDispatcher + CoroutineExceptionHandler { _, throwable ->
            _stateUI.value = _stateUI.value.copy(error = throwable.toString())
        }) {
            repo.getCharacterList(needsUpdate).validateResult(
                currentState = _stateUI, dispatcher = mainDispatcher
            ) { state ->
                _stateUI.value = state
            }
        }
    }

    fun setErrorNull() {
        _stateUI.value = _stateUI.value.copy(error = null, isLoading = false)
    }

}