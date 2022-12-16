package com.yaritzama.marvelapp.core.utils

import androidx.compose.runtime.MutableState
import com.yaritzama.marvelapp.domain.model.BaseResult
import com.yaritzama.marvelapp.presentation.ui.state.StateUI
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

suspend fun <T> BaseResult<T>.validateResult(
    currentState: MutableState<StateUI<T>>,
    dispatcher: CoroutineDispatcher,
    state: (StateUI<T>) -> Unit,
) {
    val result = this
    withContext(dispatcher) {
        when (result) {
            is BaseResult.Success ->
                state(currentState.value.copy(result.data, isLoading = false, error = null))
            is BaseResult.Error ->
                state(currentState.value.copy(error = result.message, isLoading = false))
        }
    }
}

