package com.yaritzama.marvelapp.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yaritzama.marvelapp.domain.model.BaseResult
import com.yaritzama.marvelapp.domain.model.CharacterModel
import com.yaritzama.marvelapp.domain.repository.MarvelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repo: MarvelRepository
): ViewModel() {

    private val _characterList = mutableStateListOf<CharacterModel>()
    val characterList: SnapshotStateList<CharacterModel>
            get() = _characterList

    private val  _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> get() = _error


    init{
        getCharacterList()
    }


    private fun getCharacterList(needsUpdate: Boolean = false){
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            _error.value = throwable.message.toString()
            _isLoading.value = false
        }) {
            when(val result = repo.getCharacterList(needsUpdate)){
                is BaseResult.Success ->{
                    withContext(Dispatchers.Main){
                        _characterList.clear()
                        _characterList.addAll(result.data)
                        _error.value = null
                    }
                }
                is BaseResult.Error ->{_error.value = result.message}
            }
            _isLoading.value = false
        }
    }


}